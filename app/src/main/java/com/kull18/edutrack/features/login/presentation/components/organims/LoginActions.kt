package com.example.cursosapp.ui.components.organisms.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kull18.edutrack.features.login.presentation.components.atoms.PrimaryButton
import com.kull18.edutrack.features.login.presentation.components.atoms.TextButtonLink
import com.kull18.edutrack.features.login.presentation.components.atoms.VerticalSpacer

@Composable
fun LoginActions(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Column(modifier = modifier.fillMaxWidth()) {

        VerticalSpacer(24)

        PrimaryButton(
            text = if (isLoading) "Iniciando..." else "Iniciar Sesión",
            onClick = onLoginClick,
            enabled = enabled,
            isLoading = isLoading
        )

        VerticalSpacer(16)
    }
}
