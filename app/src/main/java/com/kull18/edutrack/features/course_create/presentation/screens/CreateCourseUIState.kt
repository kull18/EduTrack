package com.kull18.edutrack.features.course_create.presentation.screens

import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionRequest
import com.kull18.edutrack.features.course_create.domain.entities.CourseCreate

data class CreateCourseUIState(
    val isLoading: Boolean = false,
    val courseRequest: CourseActionRequest? = null,
    val courseCreateResponse: CourseCreate? = null,
    val isSuccess: Boolean = false,
    val error: String? = null
)
