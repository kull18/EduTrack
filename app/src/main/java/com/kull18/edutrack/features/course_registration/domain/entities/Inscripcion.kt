package com.kull18.edutrack.features.course_registration.domain.entities

data class Inscripcion(
    val id: Int,
    val cursoId: Int,
    val cursoNombre: String,
    val cursoDescripcion: String,
    val duracionHoras: Int,
    val instructorNombre: String,
    val estado: String,
    val progreso: Double,
    val fechaInscripcion: String
)

