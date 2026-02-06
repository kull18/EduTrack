// organisms/DeleteCourseDialogOrganism.kt
package com.kull18.edutrack.features.course_delete.presentation.components.organisms

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_delete.presentation.components.atoms.DialogTitleAtom
import com.kull18.edutrack.features.course_delete.presentation.components.molecules.DialogActionsMolecule
import com.kull18.edutrack.features.course_delete.presentation.components.molecules.DialogContentMolecule

@Composable
fun DeleteCourseDialogOrganism(
    courseName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    isLoading: Boolean = false
) {
    AlertDialog(
        onDismissRequest = { if (!isLoading) onDismiss() },
        shape = RoundedCornerShape(16.dp),
        title = {
            DialogTitleAtom(text = "Eliminar curso")
        },
        text = {
            DialogContentMolecule(courseName = courseName)
        },
        confirmButton = {
            DialogActionsMolecule(
                onConfirm = onConfirm,
                onDismiss = onDismiss,
                isLoading = isLoading
            )
        }
    )
}