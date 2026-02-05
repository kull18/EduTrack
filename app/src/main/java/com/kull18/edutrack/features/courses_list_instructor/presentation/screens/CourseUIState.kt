package com.kull18.edutrack.features.courses_list_instructor.presentation.screens

import com.kull18.edutrack.features.courses_list_instructor.domain.entities.Course

data class CourseUIState(
    val isLoading: Boolean = false,
    val courses: List<Course> = emptyList(),
    val error: String? = null
)