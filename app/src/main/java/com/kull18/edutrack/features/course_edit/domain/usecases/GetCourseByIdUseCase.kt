// domain/usecases/GetCourseByIdUseCase.kt
package com.kull18.edutrack.features.course_edit.domain.usecases

import com.kull18.edutrack.features.course_edit.domain.entities.Course
import com.kull18.edutrack.features.course_edit.domain.repositories.CourseEditRepository

class GetCourseByIdUseCase(
    private val repository: CourseEditRepository
) {
    suspend operator fun invoke(courseId: Int): Result<Course> {
        return try {
            val course = repository.getCourseById(courseId)
            Result.success(course)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}