package com.example.app.ui.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.ui.molecules.ActionButtonsMolecule
import com.example.app.ui.molecules.FormFieldMolecule

@Composable
fun CourseFormOrganism(
    courseName: String,
    onCourseNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    duration: String,
    onDurationChange: (String) -> Unit,
    onCancel: () -> Unit,
    onSave: () -> Unit,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        FormFieldMolecule(
            label = "Nombre del curso",
            value = courseName,
            onValueChange = onCourseNameChange,
            placeholder = "Introduzca el nombre del curso",
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormFieldMolecule(
            label = "Descripción",
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = "Describa el contenido del curso y sus objetivos",
            singleLine = false,
            maxLines = 5,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormFieldMolecule(
            label = "Duración (horas)",
            value = duration,
            onValueChange = onDurationChange,
            placeholder = "Ej: 4",
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.weight(1f))

        ActionButtonsMolecule(
            onCancel = onCancel,
            onSave = onSave,
            isLoading = isLoading
        )
    }
}