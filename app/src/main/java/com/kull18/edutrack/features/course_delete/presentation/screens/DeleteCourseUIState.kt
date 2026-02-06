package com.kull18.edutrack.features.course_delete.presentation.screens

import com.kull18.edutrack.features.course_delete.domain.entities.CourseDelete

data class DeleteCourseUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val message: CourseDelete? = null
)