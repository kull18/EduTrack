package com.kull18.edutrack.features.courses_list.presentation.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryBlue = Color(0xFF3D5AFE)
private val RedDelete = Color(0xFFEF4444)
private val BorderColor = Color(0xFFE5E7EB)

@Composable
fun CourseActions(
    onEditClick: () -> Unit,
    onViewClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onLessonsClick: () -> Unit,          // ← nuevo
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón Lecciones — prominente, ocupa el mayor espacio
        Button(
            onClick = onLessonsClick,
            modifier = Modifier.weight(2f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.White
            )
            Text(
                text = "  Lecciones",
                fontSize = 13.sp,
                color = Color.White
            )
        }

        // Ver detalle
        OutlinedButton(
            onClick = onViewClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
        ) {
            Icon(
                imageVector = Icons.Default.Visibility,
                contentDescription = "Ver",
                modifier = Modifier.size(16.dp),
                tint = PrimaryBlue
            )
        }

        // Editar
        OutlinedButton(
            onClick = onEditClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                modifier = Modifier.size(16.dp),
                tint = PrimaryBlue
            )
        }

        // Eliminar
        OutlinedButton(
            onClick = onDeleteClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFE0E0))
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                modifier = Modifier.size(16.dp),
                tint = RedDelete
            )
        }
    }
}