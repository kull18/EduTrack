// molecules/DialogContentMolecule.kt
package com.kull18.edutrack.features.course_delete.presentation.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_delete.presentation.components.atoms.DialogTextAtom

@Composable
fun DialogContentMolecule(
    courseName: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        DialogTextAtom(
            text = "¿Estás seguro de que deseas eliminar el curso:",
            color = Color.Gray,
            fontSize = 16
        )

        Spacer(modifier = Modifier.height(8.dp))

        DialogTextAtom(
            text = "\"$courseName\"?",
            color = Color.Black,
            fontSize = 16,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        DialogTextAtom(
            text = "Esta acción no se puede deshacer.",
            color = Color.Red,
            fontSize = 14
        )
    }
}