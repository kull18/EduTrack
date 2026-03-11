package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import javax.inject.Inject

class EnrollInCourseUseCase @Inject constructor(
    private val repository: CourseRegistrationRepository
) {
    suspend operator fun invoke(courseId: Int): Result<String> {
        return try {
            Result.success(repository.enrollInCourse(courseId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

