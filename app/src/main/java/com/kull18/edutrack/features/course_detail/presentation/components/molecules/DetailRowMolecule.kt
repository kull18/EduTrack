// molecules/DetailRowMolecule.kt
package com.kull18.edutrack.features.course_detail.presentation.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_detail.presentation.components.atoms.DetailValueAtom
import com.kull18.edutrack.features.course_detail.presentation.components.atoms.IconLabelAtom

@Composable
fun DetailRowMolecule(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        IconLabelAtom(icon = icon, label = label)
        Spacer(modifier = Modifier.height(8.dp))
        DetailValueAtom(text = value)
    }
}