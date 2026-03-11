package com.kull18.edutrack.features.course_registration.domain.entities

data class RegistrationCourse(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val duracionHoras: Int,
    val instructorNombre: String,
    val activo: Boolean
)

