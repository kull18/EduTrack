package com.kull18.edutrack.features.course_edit.data.datasources.models

import com.google.gson.annotations.SerializedName

data class CourseEditResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("curso")
    val curso: CourseEditDTO
)

data class CourseEditDTO(
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

    @SerializedName("activo")
    val activo: Boolean,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
)
