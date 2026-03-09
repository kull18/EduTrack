// domain/usecases/GetCourseByIdUseCase.kt
package com.kull18.edutrack.features.course_detail.domain.usecases

import com.kull18.edutrack.features.course_detail.domain.entities.CourseDetial
import com.kull18.edutrack.features.course_detail.domain.repositories.CourseDetailRepository
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(
    private val repository: CourseDetailRepository
) {
    suspend operator fun invoke(courseId: Int): Result<CourseDetial> {
        return try {
            val course = repository.getCourseByIdDetail(courseId)
            Result.success(course)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}