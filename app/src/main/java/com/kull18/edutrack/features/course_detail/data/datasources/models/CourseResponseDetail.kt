package com.kull18.edutrack.features.course_detail.data.datasources.models

import com.google.gson.annotations.SerializedName
import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse

data class CourseDetailDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("duracion_horas")
    val duracionHoras: Int,

    @SerializedName("instructor_id")
    val instructorId: Int,

    @SerializedName("instructor")
    val instructor: LoginResponse? = null,

    @SerializedName("activo")
    val activo: Boolean,

    @SerializedName("created_at")  // ← API usa snake_case
    val createdAt: String,

    @SerializedName("updated_at")  // ← API usa snake_case
    val updatedAt: String
)