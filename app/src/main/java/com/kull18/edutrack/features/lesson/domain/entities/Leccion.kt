package com.kull18.edutrack.features.lesson.domain.entities

data class Leccion(
    val id: Int,
    val cursoId: Int,
    val titulo: String,
    val contenido: String,
    val imagenUrl: String?,
    val orden: Int,
    val duracionMinutos: Int,
    val createdAt: String,
    val updatedAt: String
)