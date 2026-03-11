package com.kull18.edutrack.features.course_registration.data.datasources.models

import com.google.gson.annotations.SerializedName

data class ProgresoRequest(
    @SerializedName("leccion_id") val leccionId: Int,
    @SerializedName("completada") val completada: Boolean
)

