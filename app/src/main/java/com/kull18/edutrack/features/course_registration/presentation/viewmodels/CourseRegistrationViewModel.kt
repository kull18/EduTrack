package com.kull18.edutrack.features.course_registration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.course_registration.domain.usecases.EnrollInCourseUseCase
import com.kull18.edutrack.features.course_registration.domain.usecases.GetAvailableCoursesUseCase
import com.kull18.edutrack.features.course_registration.domain.usecases.GetMisInscripcionesUseCase
import com.kull18.edutrack.features.course_registration.domain.usecases.GetProgresoUseCase
import com.kull18.edutrack.features.course_registration.presentation.screens.CourseRegistrationUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseRegistrationViewModel @Inject constructor(
    private val getAvailableCoursesUseCase: GetAvailableCoursesUseCase,
    private val enrollInCourseUseCase: EnrollInCourseUseCase,
    private val getMisInscripcionesUseCase: GetMisInscripcionesUseCase,
    private val getProgresoUseCase: GetProgresoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CourseRegistrationUIState())
    val uiState = _uiState.asStateFlow()

    val categories = listOf("Todos", "Diseno", "Software", "Marketing")

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            // Cargar cursos disponibles
            val coursesResult = getAvailableCoursesUseCase()
            // Cargar mis inscripciones para saber cuáles ya están inscritos
            val inscripcionesResult = getMisInscripcionesUseCase()
            val inscripcionesConProgreso = inscripcionesResult.getOrDefault(emptyList())
                .let { enrichInscripcionesWithProgress(it) }

            _uiState.update { current ->
                val courses = coursesResult.getOrDefault(emptyList())
                val inscripciones = inscripcionesConProgreso
                val enrolledIds = inscripciones.map { it.cursoId }.toSet()

                val error = coursesResult.exceptionOrNull()?.message
                    ?: inscripcionesResult.exceptionOrNull()?.message

                current.copy(
                    isLoading = false,
                    courses = courses,
                    inscripciones = inscripciones,
                    enrolledCourseIds = enrolledIds,
                    error = error
                )
            }
        }
    }

    private suspend fun enrichInscripcionesWithProgress(
        inscripciones: List<com.kull18.edutrack.features.course_registration.domain.entities.Inscripcion>
    ): List<com.kull18.edutrack.features.course_registration.domain.entities.Inscripcion> = coroutineScope {
        inscripciones.map { inscripcion ->
            async {
                val progresoResult = getProgresoUseCase(inscripcion.id)
                val progreso = progresoResult.getOrNull()

                if (progreso == null) {
                    inscripcion
                } else {
                    val porcentaje = when {
                        (progreso.totalLecciones ?: 0) > 0 -> {
                            (progreso.leccionesCompletadas ?: 0).toDouble() /
                                (progreso.totalLecciones ?: 1).toDouble()
                        }
                        progreso.progresoTotal != null -> {
                            if (progreso.progresoTotal > 1.0) {
                                progreso.progresoTotal / 100.0
                            } else {
                                progreso.progresoTotal
                            }
                        }
                        else -> 0.0
                    }.coerceIn(0.0, 1.0)

                    inscripcion.copy(
                        progreso = porcentaje,
                        estado = if (porcentaje >= 1.0) "completado" else "activo"
                    )
                }
            }
        }.awaitAll()
    }

    fun loadCourses() {
        loadData()
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onCategorySelected(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun enroll(courseId: Int) {
        if (courseId in _uiState.value.enrollingCourseIds || courseId in _uiState.value.enrolledCourseIds) {
            return
        }

        _uiState.update { current ->
            current.copy(enrollingCourseIds = current.enrollingCourseIds + courseId, error = null)
        }

        viewModelScope.launch {
            val result = enrollInCourseUseCase(courseId)
            _uiState.update { current ->
                result.fold(
                    onSuccess = { message ->
                        current.copy(
                            enrollingCourseIds = current.enrollingCourseIds - courseId,
                            enrolledCourseIds = current.enrolledCourseIds + courseId,
                            successMessage = message,
                            lastEnrolledCourseId = courseId
                        )
                    },
                    onFailure = { throwable ->
                        current.copy(
                            enrollingCourseIds = current.enrollingCourseIds - courseId,
                            error = throwable.message ?: "No se pudo completar la inscripcion"
                        )
                    }
                )
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun clearSuccessMessage() {
        _uiState.update { it.copy(successMessage = null) }
    }

    fun clearLastEnrolled() {
        _uiState.update { it.copy(lastEnrolledCourseId = null) }
    }
}

