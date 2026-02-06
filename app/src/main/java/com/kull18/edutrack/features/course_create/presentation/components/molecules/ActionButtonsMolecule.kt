package com.example.app.ui.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_create.presentation.components.atoms.PrimaryButtonAtom
import com.kull18.edutrack.features.course_create.presentation.components.atoms.SecondaryButtonAtom

@Composable
fun ActionButtonsMolecule(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SecondaryButtonAtom(
            text = "Cancelar",
            onClick = onCancel,
            enabled = !isLoading,
            modifier = Modifier.weight(1f)
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.weight(1f))
        } else {
            PrimaryButtonAtom(
                text = "Guardar",
                onClick = onSave,
                modifier = Modifier.weight(1f)
            )
        }
    }
}