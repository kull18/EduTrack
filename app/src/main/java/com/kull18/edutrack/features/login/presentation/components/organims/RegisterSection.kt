package com.example.cursosapp.ui.components.organisms.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kull18.edutrack.features.login.presentation.components.atoms.DividerWithText
import com.kull18.edutrack.features.login.presentation.components.atoms.SecondaryButton
import com.kull18.edutrack.features.login.presentation.components.atoms.VerticalSpacer

@Composable
fun RegisterSection(
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(24)
        DividerWithText(text = "o")
        VerticalSpacer(24)

        SecondaryButton(
            text = "Registrarse",
            onClick = onRegisterClick,
            enabled = enabled
        )
    }
}
