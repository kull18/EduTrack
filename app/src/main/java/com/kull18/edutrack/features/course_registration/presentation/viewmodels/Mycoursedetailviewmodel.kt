package com.kull18.edutrack.features.course_registration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.course_registration.domain.usecases.GetProgresoDaoUseCase
import com.kull18.edutrack.features.course_registration.domain.usecases.ToggleLeccionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCourseDetailViewModel @Inject constructor(
    private val getProgresoUseCase: GetProgresoDaoUseCase,
    private val toggleLeccionUseCase: ToggleLeccionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyCourseDetailUIState())
    val uiState: StateFlow<MyCourseDetailUIState> = _uiState.asStateFlow()

    fun loadProgreso(courseId: Int) {
        viewModelScope.launch {
            getProgresoUseCase(courseId).collect { result ->
                result.fold(
                    onSuccess = { progreso ->
                        _uiState.update {
                            it.copy(isLoading = false, progreso = progreso)
                        }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(isLoading = false, error = error.message)
                        }
                    }
                )
            }
        }
    }

    fun toggleLeccion(leccionId: Int, completada: Boolean) {
        viewModelScope.launch {
            toggleLeccionUseCase(leccionId, completada).onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}