package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.data.datasources.models.ProgresoResponse
import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import javax.inject.Inject

class GetProgresoUseCase @Inject constructor(
    private val repository: CourseRegistrationRepository
) {
    suspend operator fun invoke(inscripcionId: Int): Result<ProgresoResponse> {
        return try {
            Result.success(repository.getProgreso(inscripcionId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

