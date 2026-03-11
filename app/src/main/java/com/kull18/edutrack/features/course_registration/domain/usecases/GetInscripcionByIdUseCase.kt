package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.domain.entities.Inscripcion
import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import javax.inject.Inject

class GetInscripcionByIdUseCase @Inject constructor(
    private val repository: CourseRegistrationRepository
) {
    suspend operator fun invoke(inscripcionId: Int): Result<Inscripcion> {
        return try {
            Result.success(repository.getInscripcionById(inscripcionId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

