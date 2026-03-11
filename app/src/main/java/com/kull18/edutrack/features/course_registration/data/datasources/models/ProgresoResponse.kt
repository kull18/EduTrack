package com.kull18.edutrack.features.course_registration.data.datasources.models

import com.google.gson.annotations.SerializedName

data class ProgresoResponse(
    @SerializedName("progreso_total") val progresoTotal: Double?,
    @SerializedName("lecciones_completadas") val leccionesCompletadas: Int?,
    @SerializedName("total_lecciones") val totalLecciones: Int?,
    @SerializedName("lecciones") val lecciones: List<LeccionProgresoDTO>?
)

data class LeccionProgresoDTO(
    @SerializedName("leccion_id") val leccionId: Int,
    @SerializedName("completada") val completada: Boolean
)

