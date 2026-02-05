package com.kull18.edutrack.features.login.presentation.components.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextButtonLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled
    ) {
        LinkText(
            text = text,
            color = if (enabled)
                MaterialTheme.colorScheme.secondary
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
