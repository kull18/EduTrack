// molecules/ActionButton.kt
package com.example.app.ui.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_edit.presentation.components.atoms.SecondaryButtonAtom
import com.kull18.edutrack.features.course_edit.presentation.components.atoms.PrimaryButtonAtom

@Composable
fun ActionButton(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    isLoading: Boolean = false,
    saveButtonText: String = "Guardar",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SecondaryButtonAtom(
            text = "Cancelar",
            onClick = onCancel,
            enabled = !isLoading,
            modifier = Modifier.weight(1f)
        )

        PrimaryButtonAtom(
            text = saveButtonText,
            onClick = onSave,
            isLoading = isLoading,
            modifier = Modifier.weight(1f)
        )
    }
}