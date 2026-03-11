package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.domain.entities.RegistrationCourse
import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import javax.inject.Inject

class GetAvailableCoursesUseCase @Inject constructor(
    private val repository: CourseRegistrationRepository
) {
    suspend operator fun invoke(): Result<List<RegistrationCourse>> {
        return try {
            Result.success(repository.getAvailableCourses())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

