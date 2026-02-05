package com.example.registro.ui.organisms

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
import com.kull18.edutrack.features.register.data.datasources.models.UserRole
import com.kull18.edutrack.features.register.presentation.components.atoms.PrimaryButton
import com.kull18.edutrack.features.register.presentation.components.molecules.DropdownField
import com.kull18.edutrack.features.register.presentation.components.molecules.InputField
import com.kull18.edutrack.features.register.presentation.components.molecules.LoginPrompt
import com.kull18.edutrack.features.register.presentation.components.molecules.PasswordInputField
import com.kull18.edutrack.features.register.presentation.components.molecules.TermsAndPrivacy

@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
    nombre: String,
    onNombreChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    rol: UserRole,
    onRolChange: (UserRole) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    isLoading: Boolean = false,
    error: String? = null,
    onClearError: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Mostrar error en Snackbar
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
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            InputField(
                label = "Nombre completo",
                value = nombre,
                onValueChange = onNombreChange,
                placeholder = "Introduce tu nombre completo",
                enabled = !isLoading
            )

            InputField(
                label = "Correo electrónico",
                value = email,
                onValueChange = onEmailChange,
                placeholder = "Introduce tu correo electrónico",
                enabled = !isLoading
            )

            PasswordInputField(
                label = "Contraseña",
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "Crea una contraseña",
                passwordVisible = passwordVisible,
                onVisibilityChange = onPasswordVisibilityChange,
                enabled = !isLoading
            )

            DropdownField(
                label = "Rol",
                value = when (rol) {
                    UserRole.alumno -> "alumno"
                    UserRole.instructor -> "instructor"
                    else -> "alumno"
                },
                options = listOf("alumno", "instructor"),
                onValueChange = { selectedRole ->
                    val userRole = when (selectedRole) {
                        "alumno" -> UserRole.alumno
                        "instructor" -> UserRole.instructor
                        else -> UserRole.alumno
                    }
                    onRolChange(userRole)
                },
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = if (isLoading) "Registrando..." else "Registrarse",
                onClick = onRegisterClick,
                enabled = !isLoading && nombre.isNotBlank() &&
                        email.isNotBlank() && password.isNotBlank()
            )

            LoginPrompt(
                onLoginClick = onLoginClick
            )

            TermsAndPrivacy()

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Indicador de carga
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Snackbar para errores
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}