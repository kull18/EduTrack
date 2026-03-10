package com.kull18.edutrack.features.lesson.presentation.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.core.hardware.domain.CamaraManager
import com.kull18.edutrack.core.hardware.domain.GaleriaManager
import com.kull18.edutrack.features.lesson.data.datasources.models.CreateLeccionRequest
import com.kull18.edutrack.features.lesson.domain.usecases.CreateLeccionUseCase
import com.kull18.edutrack.features.lesson.presentation.screens.LessonUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateLessonViewModel @Inject constructor(
    private val createLeccionUseCase: CreateLeccionUseCase,
    private val camaraManager: CamaraManager,
    private val galeriaManager: GaleriaManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonUIState())
    val uiState = _uiState.asStateFlow()

    private val _titulo = MutableStateFlow("")
    val titulo = _titulo.asStateFlow()

    private val _contenido = MutableStateFlow("")
    val contenido = _contenido.asStateFlow()

    private val _orden = MutableStateFlow<Int?>(null)
    val orden = _orden.asStateFlow()

    private val _duracionMinutos = MutableStateFlow<Int?>(null)
    val duracionMinutos = _duracionMinutos.asStateFlow()

    private val _imagenUri = MutableStateFlow<Uri?>(null)
    val imagenUri = _imagenUri.asStateFlow()

    private var imagenFile: File? = null
    private var cursoIdActual: Int = 0

    // ─── Exponer manager para registrar launcher en Screen ──
    fun getGaleriaManager() = galeriaManager

    // ─── CursoId ───────────────────────────────────────────
    fun setCursoId(cursoId: Int) {
        cursoIdActual = cursoId
    }

    // ─── Setters formulario ────────────────────────────────
    fun onTituloChange(value: String) { _titulo.value = value }
    fun onContenidoChange(value: String) { _contenido.value = value }
    fun onOrdenChange(value: Int?) { _orden.value = value }
    fun onDuracionChange(value: Int?) { _duracionMinutos.value = value }

    fun onImagenSeleccionada(uri: Uri, file: File) {
        _imagenUri.value = uri
        imagenFile = file
    }

    fun onImagenEliminada() {
        _imagenUri.value = null
        imagenFile = null
    }

    // ─── Hardware: Cámara ──────────────────────────────────
    fun tomarFoto(context: Context) {
        camaraManager.takePhoto(
            onSuccess = { bytes ->
                val file = File(context.cacheDir, "camara_${System.currentTimeMillis()}.jpg")
                file.writeBytes(bytes)
                val uri = Uri.fromFile(file)
                onImagenSeleccionada(uri, file)
            },
            onError = { e ->
                _uiState.update { it.copy(error = e.message ?: "Error al tomar foto") }
            }
        )
    }

    // ─── Hardware: Galería ─────────────────────────────────
    // La Screen registra el launcher en AndroidGaleriaManager,
    // el ViewModel solo llama pickImage() que internamente usa ese launcher
    fun abrirGaleria(context: Context) {
        galeriaManager.pickImage(
            onSuccess = { uri ->
                val file = uriToFile(context, uri)
                onImagenSeleccionada(uri, file)
            },
            onError = { e ->
                _uiState.update { it.copy(error = e.message ?: "Error al seleccionar imagen") }
            }
        )
    }

    // ─── Crear lección ─────────────────────────────────────
    fun createLeccion() {
        if (!validarFormulario()) return

        val request = CreateLeccionRequest(
            titulo = _titulo.value,
            contenido = _contenido.value,
            orden = _orden.value!!,
            duracionMinutos = _duracionMinutos.value!!
        )

        _uiState.update { it.copy(isLoading = true, error = null, isSuccess = false) }
        viewModelScope.launch {
            createLeccionUseCase(cursoIdActual, request, imagenFile).fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                    resetFormulario()
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
            )
        }
    }

    // ─── Helpers ───────────────────────────────────────────
    private fun validarFormulario(): Boolean {
        if (_titulo.value.isBlank()) {
            _uiState.update { it.copy(error = "El título es requerido") }
            return false
        }
        if (_contenido.value.isBlank()) {
            _uiState.update { it.copy(error = "El contenido es requerido") }
            return false
        }
        if (_orden.value == null || _orden.value!! <= 0) {
            _uiState.update { it.copy(error = "El orden debe ser mayor a 0") }
            return false
        }
        if (_duracionMinutos.value == null || _duracionMinutos.value!! <= 0) {
            _uiState.update { it.copy(error = "La duración debe ser mayor a 0") }
            return false
        }
        return true
    }

    private fun resetFormulario() {
        _titulo.value = ""
        _contenido.value = ""
        _orden.value = null
        _duracionMinutos.value = null
        _imagenUri.value = null
        imagenFile = null
    }

    fun clearError() { _uiState.update { it.copy(error = null) } }
    fun resetSuccess() { _uiState.update { it.copy(isSuccess = false) } }
}

// ─── Helper Uri → File ─────────────────────────────────────
fun uriToFile(context: Context, uri: Uri): File {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "galeria_${System.currentTimeMillis()}.jpg")
    file.outputStream().use { inputStream?.copyTo(it) }
    return file
}