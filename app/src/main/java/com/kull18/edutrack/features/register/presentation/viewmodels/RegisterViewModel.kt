package com.kull18.edutrack.features.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kull18.edutrack.features.auth.domain.usecases.RegisterUseCase
import com.kull18.edutrack.features.register.data.datasources.models.RegisterRequest
import com.kull18.edutrack.features.register.data.datasources.models.UserRole
import com.kull18.edutrack.features.register.presentation.screens.RegisterUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState = _uiState.asStateFlow()

    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _rol = MutableStateFlow(UserRole.alumno)
    val rol = _rol.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible = _passwordVisible.asStateFlow()

    fun onPasswordVisibilityChange(visible: Boolean) {
        _passwordVisible.value = visible
    }

    fun onNombreChange(value: String) {
        _nombre.value = value
    }

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun onRolChange(value: UserRole) {
        _rol.value = value
    }

    // Registro
    fun register() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = registerUseCase(
                RegisterRequest(
                    nombre = _nombre.value,
                    email = _email.value,
                    password = _password.value,
                    rol = _rol.value
                )
            )

            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { user ->
                        currentState.copy(
                            isLoading = false,
                            user = user,
                            isRegistered = true
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

    // Limpiar error
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
