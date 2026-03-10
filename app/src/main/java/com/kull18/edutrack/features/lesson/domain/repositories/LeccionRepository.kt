package com.kull18.edutrack.features.lesson.domain.repositories

import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.data.datasources.models.CreateLeccionRequest
import com.kull18.edutrack.features.lesson.data.datasources.models.UpdateLeccionRequest
import java.io.File

interface LeccionRepository {

    suspend fun getLeccionesByCurso(cursoId: Int): List<Leccion>

    suspend fun getLeccionById(cursoId: Int, leccionId: Int): Leccion

    suspend fun createLeccion(
        cursoId: Int,
        request: CreateLeccionRequest,
        imagen: File?
    ): Leccion

    suspend fun updateLeccion(
        cursoId: Int,
        leccionId: Int,
        request: UpdateLeccionRequest
    ): Leccion

    suspend fun deleteLeccion(cursoId: Int, leccionId: Int): Leccion
}