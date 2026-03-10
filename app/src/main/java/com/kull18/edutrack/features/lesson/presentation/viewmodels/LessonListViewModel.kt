package com.kull18.edutrack.features.lesson.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.domain.usecases.DeleteLeccionUseCase
import com.kull18.edutrack.features.lesson.domain.usecases.GetLeccionesByCursoUseCase
import com.kull18.edutrack.features.lesson.presentation.screens.LessonUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonListViewModel @Inject constructor(
    private val getLeccionesByCursoUseCase: GetLeccionesByCursoUseCase,
    private val deleteLeccionUseCase: DeleteLeccionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonUIState())
    val uiState = _uiState.asStateFlow()

    private var cursoIdActual: Int = 0

    // ─── Cargar lecciones ──────────────────────────────
    fun loadLecciones(cursoId: Int) {
        cursoIdActual = cursoId
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            getLeccionesByCursoUseCase(cursoId).fold(
                onSuccess = { lecciones ->
                    _uiState.update { it.copy(isLoading = false, lecciones = lecciones) }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
            )
        }
    }

    // ─── Mostrar diálogo eliminar ──────────────────────
    fun onDeleteClick(leccion: Leccion) {
        _uiState.update { it.copy(showDeleteDialog = true, leccionAEliminar = leccion) }
    }

    fun onDeleteDismiss() {
        _uiState.update { it.copy(showDeleteDialog = false, leccionAEliminar = null) }
    }

    fun confirmDelete() {
        val leccion = _uiState.value.leccionAEliminar ?: return
        _uiState.update { it.copy(isLoading = true, showDeleteDialog = false) }
        viewModelScope.launch {
            deleteLeccionUseCase(cursoIdActual, leccion.id).fold(
                onSuccess = {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            leccionAEliminar = null,
                            lecciones = state.lecciones.filter { it.id != leccion.id }
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}