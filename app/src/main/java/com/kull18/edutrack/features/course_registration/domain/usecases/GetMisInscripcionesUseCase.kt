package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.domain.entities.Inscripcion
import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import javax.inject.Inject

class GetMisInscripcionesUseCase @Inject constructor(
    private val repository: CourseRegistrationRepository
) {
    suspend operator fun invoke(): Result<List<Inscripcion>> {
        return try {
            Result.success(repository.getMisInscripciones())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

