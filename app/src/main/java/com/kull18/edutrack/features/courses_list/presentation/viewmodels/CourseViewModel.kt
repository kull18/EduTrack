package com.kull18.edutrack.features.courses_list.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.courses_list.domain.usecases.GetAllCoursesUseCase
import com.kull18.edutrack.features.courses_list.presentation.screens.CourseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val getAllCoursesUseCase: GetAllCoursesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CourseUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getCourses()
    }

    fun getCourses() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = getAllCoursesUseCase()
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { courses ->
                        currentState.copy(
                            isLoading = false,
                            courses = courses
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
