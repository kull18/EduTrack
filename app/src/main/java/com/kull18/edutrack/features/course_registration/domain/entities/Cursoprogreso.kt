package com.kull18.edutrack.features.course_registration.domain.entities

data class CursoProgreso(
    val courseId: Int,
    val courseName: String,
    val instructorNombre: String,
    val duracionTotalMin: Int,
    val lecciones: List<LeccionProgreso>,
    val totalLecciones: Int,
    val completadas: Int,
    val porcentaje: Float          // 0f..1f
)