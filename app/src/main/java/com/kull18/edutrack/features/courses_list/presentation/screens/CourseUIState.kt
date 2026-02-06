package com.kull18.edutrack.features.courses_list.presentation.screens

import com.kull18.edutrack.features.courses_list.domain.entities.Course

data class CourseUIState(
    val isLoading: Boolean = false,
    val courses: List<Course> = emptyList(),
    val error: String? = null
)