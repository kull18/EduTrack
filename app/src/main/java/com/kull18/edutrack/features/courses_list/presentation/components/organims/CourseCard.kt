package com.kull18.edutrack.features.courses_list.presentation.components.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kull18.edutrack.features.courses_list.presentation.components.molecules.CourseActions
import com.kull18.edutrack.features.courses_list.presentation.components.molecules.CourseHeader

@Composable
fun CourseCard(
    title: String,
    isActive: Boolean,
    onEditClick: () -> Unit,
    onViewClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onLessonsClick: () -> Unit,          // ← nuevo
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CourseHeader(
            title = title,
            isActive = isActive
        )

        HorizontalDivider(color = Color(0xFFE5E5E5))

        CourseActions(
            onEditClick = onEditClick,
            onViewClick = onViewClick,
            onDeleteClick = onDeleteClick,
            onLessonsClick = onLessonsClick   // ← nuevo
        )
    }
}