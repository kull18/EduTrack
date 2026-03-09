// domain/usecases/UpdateCourseUseCase.kt
package com.kull18.edutrack.features.course_edit.domain.usecases

import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditRequest
import com.kull18.edutrack.features.course_edit.domain.entities.CourseEdit
import com.kull18.edutrack.features.course_edit.domain.repositories.CourseEditRepository
import javax.inject.Inject

class UpdateCourseUseCase @Inject constructor(
    private val repository: CourseEditRepository
) {
    suspend operator fun invoke(courseId: Int, course: CourseEditRequest): Result<CourseEdit> {
        return try {
            val updatedCourse = repository.updateCourse(courseId, course)
            Result.success(updatedCourse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}