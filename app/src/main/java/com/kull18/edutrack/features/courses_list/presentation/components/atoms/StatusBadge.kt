// presentation/components/atoms/StatusBadge.kt
package com.kull18.edutrack.features.courses_list.presentation.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusBadge(
    text: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .background(
                color = if (isActive) Color(0xFF5B67F7) else Color(0xFFE5E5E5),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        color = if (isActive) Color.White else Color.Gray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    )
}