package com.kull18.edutrack.features.lesson.data.datasources.mapper

import com.kull18.edutrack.BuildConfig
import com.kull18.edutrack.features.lesson.data.datasources.models.LeccionDTO
import com.kull18.edutrack.features.lesson.data.datasources.models.LeccionResponse
import com.kull18.edutrack.features.lesson.domain.entities.Leccion

private val BASE_URL = BuildConfig.API_URL.trimEnd('/')

private fun String?.toFullUrl(): String? {
    if (this == null) return null
    return if (this.startsWith("http")) this else "$BASE_URL$this"
}

fun LeccionDTO.toDomain(): Leccion {
    return Leccion(
        id = this.id,
        cursoId = this.cursoId,
        titulo = this.titulo ?: "",
        contenido = this.contenido ?: "",
        imagenUrl = this.imagenUrl.toFullUrl(),
        orden = this.orden,
        duracionMinutos = this.duracionMinutos,
        createdAt = this.createdAt ?: "",
        updatedAt = this.updatedAt ?: ""
    )
}

fun LeccionResponse.toDomain(): Leccion {
    val dto = this.leccion
        ?: throw IllegalStateException("La respuesta no contiene el objeto 'leccion'")
    return dto.toDomain()
}