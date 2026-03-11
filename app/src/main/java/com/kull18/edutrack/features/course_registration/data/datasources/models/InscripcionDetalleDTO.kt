package com.kull18.edutrack.features.course_registration.data.datasources.models

import com.google.gson.annotations.SerializedName
import com.kull18.edutrack.features.courses_list.data.datasources.models.CourseDTO

data class InscripcionDetalleDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("usuario_id") val usuarioId: Int,
    @SerializedName("curso_id") val cursoId: Int,
    @SerializedName("estado") val estado: String?,
    @SerializedName("progreso") val progreso: Double?,
    @SerializedName("curso") val curso: CourseDTO?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)

