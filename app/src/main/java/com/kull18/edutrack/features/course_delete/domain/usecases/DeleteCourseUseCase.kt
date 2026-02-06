
package com.kull18.edutrack.features.course_delete.domain.usecases

import com.kull18.edutrack.features.course_delete.domain.entities.CourseDelete
import com.kull18.edutrack.features.course_delete.domain.repositories.CourseDeleteRepository

class DeleteCourseUseCase(
    private val repository: CourseDeleteRepository
) {
    suspend operator fun invoke(courseId: Int): Result<CourseDelete> {
        return try {
            val message = repository.deleteCourse(courseId)
            Result.success(message)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}