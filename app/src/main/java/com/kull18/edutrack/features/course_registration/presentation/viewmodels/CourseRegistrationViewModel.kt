package com.kull18.edutrack.features.course_registration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.course_registration.domain.usecases.EnrollInCourseUseCase
import com.kull18.edutrack.features.course_registration.domain.usecases.GetAvailableCoursesUseCase
import com.kull18.edutrack.features.course_registration.domain.usecases.GetMisInscripcionesUseCase
import com.kull18.edutrack.features.course_registration.presentation.screens.CourseRegistrationUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseRegistrationViewModel @Inject constructor(
    private val getAvailableCoursesUseCase: GetAvailableCoursesUseCase,
    private val enrollInCourseUseCase: EnrollInCourseUseCase,
    private val getMisInscripcionesUseCase: GetMisInscripcionesUseCase
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

            _uiState.update { current ->
                val courses = coursesResult.getOrDefault(emptyList())
                val inscripciones = inscripcionesResult.getOrDefault(emptyList())
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
                            successMessage = message
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
}

