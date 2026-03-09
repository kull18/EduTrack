package com.kull18.edutrack.features.course_create.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionRequest
import com.kull18.edutrack.features.course_create.domain.usecases.CreateCourseUseCase
import com.kull18.edutrack.features.course_create.presentation.screens.CreateCourseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCourseViewModel @Inject constructor(
    private val createCourseUseCase: CreateCourseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateCourseUIState())
    val uiState = _uiState.asStateFlow()

    private val _courseName = MutableStateFlow("")
    val courseName = _courseName.asStateFlow()

    private val _courseDescription = MutableStateFlow("")
    val courseDescription = _courseDescription.asStateFlow()

    private val _courseDuration = MutableStateFlow<Int?>(null) // Cambiar a Int? nullable
    val courseDuration = _courseDuration.asStateFlow()

    fun onCourseNameChange(value: String) {
        _courseName.value = value
    }

    fun onCourseDescriptionChange(value: String) {
        _courseDescription.value = value
    }

    fun onCourseDurationChange(value: Int?) {
        println("Duration change: $value")
        _courseDuration.value = value
    }


    fun createCourse() {
        // Validar antes de crear
        if (_courseDuration.value == null || _courseDuration.value!! <= 0) {
            _uiState.update { it.copy(error = "La duración debe ser mayor a 0") }
            return
        }

        val courseRequest = CourseActionRequest(
            nombre = _courseName.value,
            descripcion = _courseDescription.value,
            duracionHoras = _courseDuration.value!! // Ya validado que no es null
        )

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = createCourseUseCase(courseRequest)

            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { courseResponse ->
                        currentState.copy(
                            isLoading = false,
                            courseRequest = courseRequest,
                            courseCreateResponse = courseResponse,
                            isSuccess = true
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