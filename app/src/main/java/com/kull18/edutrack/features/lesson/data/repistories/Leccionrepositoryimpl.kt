package com.kull18.edutrack.features.lesson.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.lesson.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.lesson.data.datasources.models.CreateLeccionRequest
import com.kull18.edutrack.features.lesson.data.datasources.models.UpdateLeccionRequest
import com.kull18.edutrack.features.lesson.domain.entities.Leccion
import com.kull18.edutrack.features.lesson.domain.repositories.LeccionRepository
import kotlinx.coroutines.flow.first
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class LeccionRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : LeccionRepository {

    private suspend fun getToken(): String {
        val token = tokenDataStore.getToken().first()
        return "Bearer $token"
    }

    override suspend fun getLeccionesByCurso(cursoId: Int): List<Leccion> {
        return api.getLeccionesByCurso(getToken(), cursoId).map { it.toDomain() }
    }

    override suspend fun getLeccionById(cursoId: Int, leccionId: Int): Leccion {
        return api.getLeccionById(getToken(), cursoId, leccionId).toDomain()
    }

    override suspend fun createLeccion(
        cursoId: Int,
        request: CreateLeccionRequest,
        imagen: File?
    ): Leccion {
        // Convertir campos a RequestBody para multipart
        val titulo = request.titulo.toRequestBody("text/plain".toMediaTypeOrNull())
        val contenido = request.contenido.toRequestBody("text/plain".toMediaTypeOrNull())
        val orden = request.orden.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val duracion = request.duracionMinutos.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        // Imagen es opcional
        val imagenPart = imagen?.let {
            val requestBody = it.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("imagen", it.name, requestBody)
        }

        return api.createLeccion(
            token = getToken(),
            cursoId = cursoId,
            titulo = titulo,
            contenido = contenido,
            orden = orden,
            duracionMinutos = duracion,
            imagen = imagenPart
        ).toDomain()
    }

    override suspend fun updateLeccion(
        cursoId: Int,
        leccionId: Int,
        request: UpdateLeccionRequest
    ): Leccion {
        return api.updateLeccion(getToken(), cursoId, leccionId, request).toDomain()
    }

    override suspend fun deleteLeccion(cursoId: Int, leccionId: Int): Leccion {
        return api.deleteLeccion(getToken(), cursoId, leccionId).toDomain()
    }
}