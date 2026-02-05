package com.example.cursosapp.ui.components.organisms.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kull18.edutrack.features.login.presentation.components.atoms.AppLogo
import com.kull18.edutrack.features.login.presentation.components.atoms.VerticalSpacer

@Composable
fun LoginHeader(
    modifier: Modifier = Modifier,
    logoSize: Int = 80
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(80)
        AppLogo(size = logoSize)
        VerticalSpacer(48)
    }
}
