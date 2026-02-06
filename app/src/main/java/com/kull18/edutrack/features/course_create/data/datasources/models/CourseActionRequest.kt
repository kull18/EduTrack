package com.kull18.edutrack.features.course_create.data.datasources.models

import com.google.gson.annotations.SerializedName

data class CourseActionRequest(
    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("duracion_horas")
    val duracionHoras: Int,

    @SerializedName("instructor_id")
    var instructorId: Int? = null
)
