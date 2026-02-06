// EditCourseUIState.kt
package com.kull18.edutrack.features.course_edit.presentation.screens

import com.kull18.edutrack.features.course_edit.domain.entities.Course
import com.kull18.edutrack.features.course_edit.domain.entities.CourseEdit

data class EditCourseUIState(
    val isLoading: Boolean = false,
    val course: Course? = null,  // Para cargar datos
    val courseEdit: CourseEdit? = null,  // Para el resultado de actualización
    val error: String? = null,
    val isSuccess: Boolean = false
)