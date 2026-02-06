// atoms/PrimaryButtonAtom.kt
package com.kull18.edutrack.features.course_edit.presentation.components.atoms

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButtonAtom(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(8.dp),
        enabled = !isLoading && enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6366F1),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFF6366F1).copy(alpha = 0.6f),
            disabledContentColor = Color.White.copy(alpha = 0.6f)
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.height(24.dp)
            )
        } else {
            Text(text = text, fontSize = 16.sp)
        }
    }
}