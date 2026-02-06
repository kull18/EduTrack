// EditCourseViewModel.kt
package com.kull18.edutrack.features.course_edit.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditRequest
import com.kull18.edutrack.features.course_edit.domain.usecases.GetCourseByIdUseCase
import com.kull18.edutrack.features.course_edit.domain.usecases.UpdateCourseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditCourseViewModel(
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditCourseUIState())
    val uiState = _uiState.asStateFlow()

    private val _courseName = MutableStateFlow("")
    val courseName = _courseName.asStateFlow()

    private val _courseDescription = MutableStateFlow("")
    val courseDescription = _courseDescription.asStateFlow()

    private val _courseDuration = MutableStateFlow<Int?>(null)
    val courseDuration = _courseDuration.asStateFlow()

    private val _courseActive = MutableStateFlow(true)
    val courseActive = _courseActive.asStateFlow()

    private var courseId: Int = 0

    fun loadCourse(id: Int) {
        courseId = id
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = getCourseByIdUseCase(id)
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { course ->
                        _courseName.value = course.nombre
                        _courseDescription.value = course.descripcion
                        _courseDuration.value = course.duracionHoras
                        _courseActive.value = course.activo

                        currentState.copy(
                            isLoading = false,
                            course = course
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                )
            }
        }
    }

    fun onCourseNameChange(value: String) {
        _courseName.value = value
    }

    fun onCourseDescriptionChange(value: String) {
        _courseDescription.value = value
    }

    fun onCourseDurationChange(value: Int?) {
        _courseDuration.value = value
    }

    fun onCourseActiveChange(value: Boolean) {
        _courseActive.value = value
    }

    fun updateCourse() {
        if (_courseDuration.value == null || _courseDuration.value!! <= 0) {
            _uiState.update { it.copy(error = "La duración debe ser mayor a 0") }
            return
        }

        val courseRequest = CourseEditRequest(
            nombre = _courseName.value,
            descripcion = _courseDescription.value,
            duracionHoras = _courseDuration.value!!,
            activo = _courseActive.value
        )

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = updateCourseUseCase(courseId, courseRequest)

            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { updatedCourse ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = true,
                            courseEdit = updatedCourse
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(
                            isLoading = false,
                            error = error.message,
                            isSuccess = false
                        )
                    }
                )
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}