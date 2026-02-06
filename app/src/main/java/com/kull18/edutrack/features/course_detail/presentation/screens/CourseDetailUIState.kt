// viewmodels/CourseDetailUIState.kt
package com.kull18.edutrack.features.course_detail.presentation.screens

import com.kull18.edutrack.features.course_detail.domain.entities.CourseDetial

data class CourseDetailUIState(
    val isLoading: Boolean = false,
    val course: CourseDetial? = null,
    val error: String? = null
)