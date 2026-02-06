package com.kull18.edutrack.features.course_edit.domain.entities

data class CourseEdit(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val duracion_horas: Int,
    val instructor_id: Int,
    val activo: Boolean,
    val created_at: String,
    val updated_at: String
)