package com.kull18.edutrack.features.lesson.domain.usecases

import com.kull18.edutrack.features.lesson.data.datasources.models.UpdateLeccionRequest
import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.domain.repositories.LeccionRepository
import javax.inject.Inject

class UpdateLeccionUseCase @Inject constructor(
    private val repository: LeccionRepository
) {
    suspend operator fun invoke(
        cursoId: Int,
        leccionId: Int,
        request: UpdateLeccionRequest
    ): Result<Leccion> {
        return try {
            Result.success(repository.updateLeccion(cursoId, leccionId, request))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}