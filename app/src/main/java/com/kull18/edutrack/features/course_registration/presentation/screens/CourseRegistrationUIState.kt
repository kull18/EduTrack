package com.kull18.edutrack.features.course_registration.presentation.screens

import com.kull18.edutrack.features.course_registration.domain.entities.RegistrationCourse

data class CourseRegistrationUIState(
    val isLoading: Boolean = false,
    val courses: List<RegistrationCourse> = emptyList(),
    val query: String = "",
    val selectedCategory: String = "Todos",
    val enrolledCourseIds: Set<Int> = emptySet(),
    val enrollingCourseIds: Set<Int> = emptySet(),
    val error: String? = null,
    val successMessage: String? = null
)

