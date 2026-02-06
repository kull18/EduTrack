// presentation/screens/DeleteCourseViewModel.kt
package com.kull18.edutrack.features.course_delete.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.course_delete.domain.usecases.DeleteCourseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeleteCourseViewModel(
    private val deleteCourseUseCase: DeleteCourseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeleteCourseUIState())
    val uiState = _uiState.asStateFlow()

    fun deleteCourse(courseId: Int) {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = deleteCourseUseCase(courseId)

            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { message ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = true,
                            message = message
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

    fun clearState() {
        _uiState.update { DeleteCourseUIState() }
    }
}