package com.kull18.edutrack.features.lesson.domain.usecases

import com.kull18.edutrack.core.hardware.domain.CamaraManager
import com.kull18.edutrack.core.hardware.domain.GaleriaManager
import com.kull18.edutrack.features.lesson.data.datasources.models.CreateLeccionRequest
import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.domain.repositories.LeccionRepository
import java.io.File
import javax.inject.Inject

class CreateLeccionUseCase @Inject constructor(
    private val repository: LeccionRepository,
    val camaraManager: CamaraManager,       // hardware inyectado directo en UseCase
    val galeriaManager: GaleriaManager
) {
    suspend operator fun invoke(
        cursoId: Int,
        request: CreateLeccionRequest,
        imagen: File?
    ): Result<Leccion> {
        return try {
            Result.success(repository.createLeccion(cursoId, request, imagen))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}