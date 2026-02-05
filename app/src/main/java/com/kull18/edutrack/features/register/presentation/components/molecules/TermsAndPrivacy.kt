package com.kull18.edutrack.features.register.presentation.components.molecules

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.register.presentation.components.atoms.BodyText

@Composable
fun TermsAndPrivacy(
    modifier: Modifier = Modifier
) {
    BodyText(
        text = "Al registrarte, aceptas nuestros Términos de Servicio y Política de Privacidad.",
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    )
}
