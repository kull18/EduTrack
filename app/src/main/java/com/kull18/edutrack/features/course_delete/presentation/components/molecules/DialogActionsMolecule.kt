// molecules/DialogActionsMolecule.kt
package com.kull18.edutrack.features.course_delete.presentation.components.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_create.presentation.components.atoms.SecondaryButtonAtom
import com.kull18.edutrack.features.course_delete.presentation.components.atoms.DangerButtonAtom

@Composable
fun DialogActionsMolecule(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SecondaryButtonAtom(
            text = "Cancelar",
            onClick = onDismiss,
            enabled = !isLoading,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        DangerButtonAtom(
            text = "Eliminar",
            onClick = onConfirm,
            isLoading = isLoading,
            modifier = Modifier.weight(1f)
        )
    }
}