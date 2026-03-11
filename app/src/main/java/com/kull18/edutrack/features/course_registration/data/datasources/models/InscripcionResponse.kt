package com.kull18.edutrack.features.course_registration.data.datasources.models

import com.google.gson.annotations.SerializedName

data class InscripcionResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("inscripcion") val inscripcion: InscripcionDTO?
)

data class InscripcionDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("usuario_id") val usuarioId: Int,
    @SerializedName("curso_id") val cursoId: Int,
    @SerializedName("estado") val estado: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)

