package com.kull18.edutrack.features.register.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.registro.ui.organisms.RegistrationForm
import com.kull18.edutrack.core.ui.components.MainScaffold
import com.kull18.edutrack.features.auth.presentation.viewmodels.RegisterViewModel
import com.kull18.edutrack.features.auth.presentation.viewmodels.RegisterViewModelFactory
import com.kull18.edutrack.features.register.presentation.components.molecules.TopBar

@Composable
fun RegisterScreen(
    factory: RegisterViewModelFactory,
    onNavigateToLogin: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {
    val viewModel: RegisterViewModel = viewModel(factory = factory)

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nombre by viewModel.nombre.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val rol by viewModel.rol.collectAsStateWithLifecycle()
    val passwordVisible by viewModel.passwordVisible.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isRegistered) {
        if (uiState.isRegistered) {
            onRegisterSuccess()
        }
    }

    MainScaffold(
        topAppBar = { TopBar("Crear cuenta", onNavigateToLogin, modifier = Modifier) },
    ) {
        RegistrationForm(
            nombre = nombre,
            onNombreChange = viewModel::onNombreChange,
            email = email,
            onEmailChange = viewModel::onEmailChange,
            password = password,
            onPasswordChange = viewModel::onPasswordChange,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = viewModel::onPasswordVisibilityChange,
            rol = rol,
            onRolChange = viewModel::onRolChange,
            onRegisterClick = { viewModel.register() },
            onLoginClick = onNavigateToLogin,
            isLoading = uiState.isLoading,
            error = uiState.error,
            onClearError = viewModel::clearError
        )
    }
}