package com.kull18.edutrack.features.course_registration.domain.entities

data class LeccionProgreso(
    val leccionId: Int,
    val inscripcionId: Int,
    val titulo: String,
    val completada: Boolean,
    val fechaCompletado: String?
)