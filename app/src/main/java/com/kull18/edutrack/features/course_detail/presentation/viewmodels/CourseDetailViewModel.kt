// viewmodels/CourseDetailViewModel.kt
package com.kull18.edutrack.features.course_detail.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.course_detail.domain.usecases.GetCourseByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDetailViewModel(
    private val getCourseByIdUseCase: GetCourseByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CourseDetailUIState())
    val uiState = _uiState.asStateFlow()

    fun loadCourseDetail(courseId: Int) {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = getCourseByIdUseCase(courseId)
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { course ->
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

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}