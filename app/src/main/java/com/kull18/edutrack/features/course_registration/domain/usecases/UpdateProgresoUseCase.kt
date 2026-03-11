package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.data.datasources.models.ProgresoResponse
import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import javax.inject.Inject

class UpdateProgresoUseCase @Inject constructor(
    private val repository: CourseRegistrationRepository
) {
    suspend operator fun invoke(
        inscripcionId: Int,
        leccionId: Int,
        completada: Boolean
    ): Result<ProgresoResponse> {
        return try {
            Result.success(repository.updateProgreso(inscripcionId, leccionId, completada))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

