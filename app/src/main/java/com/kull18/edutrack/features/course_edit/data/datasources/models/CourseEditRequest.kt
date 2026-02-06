package com.kull18.edutrack.features.course_edit.data.datasources.models

import com.google.gson.annotations.SerializedName

data class CourseEditRequest(
    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("duracion_horas")
    val duracionHoras: Int,

    @SerializedName("activo")
    val activo: Boolean
)