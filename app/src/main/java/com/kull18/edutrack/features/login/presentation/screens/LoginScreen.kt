// presentation/screens/LoginScreen.kt (actualización)
package com.kull18.edutrack.features.login.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursosapp.ui.components.organisms.login.LoginContent
import com.example.cursosapp.ui.components.organisms.login.LoginHeader
import com.kull18.edutrack.core.theme.ui.EduTrackTheme
import com.kull18.edutrack.core.ui.components.MainScaffold
import com.kull18.edutrack.features.auth.presentation.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit = {},
    onLoginSuccess: (String) -> Unit = {} // Recibe el rol del usuario
) {
    val viewModel: LoginViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val visiblePassword by viewModel.passwordVisible.collectAsStateWithLifecycle()

    // Navegar cuando el login es exitoso
    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn && uiState.user != null) {
            onLoginSuccess(uiState.user!!.rol)
        }
    }

    MainScaffold {
        LoginHeader()
        LoginContent(
            email = email,
            onEmailChange = { viewModel.onEmailChange(it) },
            password = password,
            onPasswordChange = { viewModel.onPasswordChange(it) },
            onLoginClick = { viewModel.login(email, password) },
            onChangeVisible = { viewModel.onPasswordVisibilityChange(it) },
            passwordVisible = visiblePassword,
            onRegisterClick = onNavigateToRegister,
            isLoading = uiState.isLoading,
            error = uiState.error,
            onClearError = viewModel::clearError
        )
    }
}
