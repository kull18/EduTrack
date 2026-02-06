// domain/repositories/CourseEditRepository.kt
package com.kull18.edutrack.features.course_edit.domain.repositories

import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditRequest
import com.kull18.edutrack.features.course_edit.domain.entities.Course
import com.kull18.edutrack.features.course_edit.domain.entities.CourseEdit

interface CourseEditRepository {
    suspend fun getCourseById(id: Int): Course
    suspend fun updateCourse(id: Int, course: CourseEditRequest): CourseEdit
}