package com.kull18.edutrack.features.lesson.domain.usecases

import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.domain.repositories.LeccionRepository
import javax.inject.Inject

class GetLeccionByIdUseCase @Inject constructor(
    private val repository: LeccionRepository
) {
    suspend operator fun invoke(cursoId: Int, leccionId: Int): Result<Leccion> {
        return try {
            Result.success(repository.getLeccionById(cursoId, leccionId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}