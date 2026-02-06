// molecules/StatusRowMolecule.kt
package com.kull18.edutrack.features.course_detail.presentation.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.course_detail.presentation.components.atoms.IconLabelAtom
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import com.kull18.edutrack.features.course_detail.presentation.components.atoms.StatusBadgeAtom

@Composable
fun StatusRowMolecule(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        IconLabelAtom(
            icon = Icons.Outlined.CheckCircle,
            label = "Estado"
        )
        Spacer(modifier = Modifier.height(8.dp))
        StatusBadgeAtom(isActive = isActive)
    }
}