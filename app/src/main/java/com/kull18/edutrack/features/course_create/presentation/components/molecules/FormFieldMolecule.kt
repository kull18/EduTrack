package com.example.app.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.ui.atoms.TextFieldAtom
import com.kull18.edutrack.features.course_create.presentation.components.atoms.LabelAtom

@Composable
fun FormFieldMolecule(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    enabled: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LabelAtom(text = label)
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldAtom(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            singleLine = singleLine,
            maxLines = maxLines,
            enabled = enabled
        )
    }
}