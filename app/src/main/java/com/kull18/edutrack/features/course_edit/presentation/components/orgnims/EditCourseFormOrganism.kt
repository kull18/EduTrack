package com.kull18.edutrack.features.course_edit.presentation.components.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.ui.molecules.ActionButton
import com.kull18.edutrack.features.course_edit.presentation.components.molecules.EditFormFieldMolecule

@Composable
fun EditCourseFormOrganism(
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
        EditFormFieldMolecule(
            label = "Nombre del curso",
            value = courseName,
            onValueChange = onCourseNameChange,
            placeholder = "Introduzca el nombre del curso",
            hint = "Un nombre claro ayuda a los estudiantes.",
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(20.dp))

        EditFormFieldMolecule(
            label = "Descripción",
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = "Describa el contenido del curso",
            singleLine = false,
            maxLines = 5,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(20.dp))

        EditFormFieldMolecule(
            label = "Duración",
            value = duration,
            onValueChange = onDurationChange,
            placeholder = "Ej: 4 horas",
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.weight(1f))

        ActionButton(
            onCancel = onCancel,
            onSave = onSave,
            isLoading = isLoading,
            saveButtonText = "Guardar cambios"
        )
    }
}