package com.kull18.edutrack.features.lesson.domain.usecases

import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.domain.repositories.LeccionRepository
import javax.inject.Inject

class GetLeccionesByCursoUseCase @Inject constructor(
    private val repository: LeccionRepository
) {
    suspend operator fun invoke(cursoId: Int): Result<List<Leccion>> {
        return try {
            Result.success(repository.getLeccionesByCurso(cursoId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}