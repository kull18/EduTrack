// organisms/login/LoginContent.kt
package com.example.cursosapp.ui.components.organisms.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.register.presentation.components.atoms.PrimaryButton
import com.kull18.edutrack.features.register.presentation.components.molecules.InputField
import com.kull18.edutrack.features.register.presentation.components.molecules.PasswordInputField

@Composable
fun LoginContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onChangeVisible: (Boolean) -> Unit,
    passwordVisible: Boolean,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    error: String? = null,
    onClearError: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(error) {
        error?.let {
            snackbarHostState.showSnackbar(it)
            onClearError()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            InputField(
                label = "Correo electrónico",
                value = email,
                onValueChange = onEmailChange,
                placeholder = "ejemplo@correo.com",
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInputField(
                label = "Contraseña",
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "Ingresa tu contraseña",
                passwordVisible = passwordVisible,
                onVisibilityChange = onChangeVisible,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = if (isLoading) "Iniciando sesión..." else "Iniciar sesión",
                onClick = onLoginClick,
                enabled = !isLoading && email.isNotBlank() && password.isNotBlank()
            )

            Spacer(modifier = Modifier.height(16.dp))

            androidx.compose.material3.TextButton(
                onClick = onRegisterClick,
                enabled = !isLoading
            ) {
                androidx.compose.material3.Text("¿No tienes cuenta? Regístrate")
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}