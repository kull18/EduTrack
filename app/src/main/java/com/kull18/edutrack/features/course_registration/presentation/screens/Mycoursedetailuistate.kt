package com.kull18.edutrack.features.course_registration.presentation.viewmodels

import com.kull18.edutrack.features.course_registration.domain.entities.CursoProgreso

data class MyCourseDetailUIState(
    val isLoading: Boolean = true,
    val progreso: CursoProgreso? = null,
    val error: String? = null
)