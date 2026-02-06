package com.kull18.edutrack.features.course_detail.domain.entities

import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse

data class CourseDetial(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val duracionHoras: Int,
    val instructorId: Int,
    val instructor: LoginResponse? = null,
    val activo: Boolean,
    val createdAt: String,
    val updatedAt: String
)