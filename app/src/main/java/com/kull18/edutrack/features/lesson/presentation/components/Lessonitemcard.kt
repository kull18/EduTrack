package com.kull18.edutrack.features.lesson.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryBlue = Color(0xFF3D5AFE)
private val LightBlue = Color(0xFFE8EEFF)
private val TextPrimary = Color(0xFF1A1A2E)
private val TextSecondary = Color(0xFF6B7280)
private val RedDelete = Color(0xFFEF4444)

@Composable
fun LessonItemCard(
    order: Int,
    title: String,
    durationMinutes: Int,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Número de orden
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(LightBlue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = order.toString().padStart(2, '0'),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Título y duración
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(3.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🕐", fontSize = 11.sp)
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "$durationMinutes minutos",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }

        // Botón editar
        IconButton(onClick = onEditClick) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = PrimaryBlue,
                modifier = Modifier.size(20.dp)
            )
        }

        // Botón eliminar
        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = RedDelete,
                modifier = Modifier.size(20.dp)
            )
        }
    }

    HorizontalDivider(color = Color(0xFFF3F4F6), thickness = 1.dp)
}