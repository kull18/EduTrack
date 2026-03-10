package com.kull18.edutrack.features.lesson.data.datasources.mapper

import com.kull18.edutrack.features.lesson.data.datasources.models.LeccionDTO
import com.kull18.edutrack.features.lesson.data.datasources.models.LeccionResponse
import com.kull18.edutrack.features.lesson.domain.entities.Leccion

fun LeccionDTO.toDomain(): Leccion {
    return Leccion(
        id = this.id,
        cursoId = this.cursoId,
        titulo = this.titulo,
        contenido = this.contenido,
        imagenUrl = this.imagenUrl,
        orden = this.orden,
        duracionMinutos = this.duracionMinutos,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun LeccionResponse.toDomain(): Leccion {
    return Leccion(
        id = this.id,
        cursoId = this.cursoId,
        titulo = this.titulo,
        contenido = this.contenido,
        imagenUrl = this.imagenUrl,
        orden = this.orden,
        duracionMinutos = this.duracionMinutos,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}