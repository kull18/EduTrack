package com.kull18.edutrack.features.courses_list_instructor.domain.entities

import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse

data class Course (
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
