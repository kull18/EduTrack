package com.kull18.edutrack.features.register.presentation.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kull18.edutrack.features.register.presentation.components.atoms.BodyText
import com.kull18.edutrack.features.register.presentation.components.atoms.ClickableText

@Composable
fun LoginPrompt(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BodyText(text = "¿Ya tienes cuenta? ")
        ClickableText(
            text = "Iniciar sesión",
            onClick = onLoginClick
        )
    }
}