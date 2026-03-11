package com.kull18.edutrack.features.lesson.domain.usecases

import com.kull18.edutrack.features.lesson.domain.repositories.LeccionRepository
import javax.inject.Inject

class DeleteLeccionUseCase @Inject constructor(
    private val repository: LeccionRepository
) {
    suspend operator fun invoke(cursoId: Int, leccionId: Int): Result<Unit> {
        return try {
            Result.success(repository.deleteLeccion(cursoId, leccionId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}