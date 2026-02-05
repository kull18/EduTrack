// presentation/components/molecules/CourseActions.kt
package com.kull18.edutrack.features.courses_list_instructor.presentation.components.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kull18.edutrack.features.courses_list_instructor.presentation.components.atoms.ActionIcon

@Composable
fun CourseActions(
    onEditClick: () -> Unit,
    onViewClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ActionIcon(
            icon = Icons.Default.Edit,
            contentDescription = "Editar",
            tint = Color(0xFF5B67F7),
            onClick = onEditClick
        )
        ActionIcon(
            icon = Icons.Outlined.RemoveRedEye,
            contentDescription = "Ver",
            tint = Color(0xFF5B67F7),
            onClick = onViewClick
        )
        ActionIcon(
            icon = Icons.Default.Delete,
            contentDescription = "Eliminar",
            tint = Color(0xFFFF6B6B),
            onClick = onDeleteClick
        )
    }
}