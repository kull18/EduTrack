package com.kull18.edutrack.features.course_registration.domain.usecases

import com.kull18.edutrack.features.course_registration.domain.repositories.MyCourseRepository
import javax.inject.Inject

class ToggleLeccionUseCase @Inject constructor(
    private val repository: MyCourseRepository
) {
    suspend operator fun invoke(leccionId: Int, completada: Boolean): Result<Unit> {
        return try {
            Result.success(repository.toggleLeccion(leccionId, completada))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}