package com.kull18.edutrack.features.lesson.data.datasources.models

import com.google.gson.annotations.SerializedName

// ─── Request crear lección ─────────────────────────────
data class CreateLeccionRequest(
    @SerializedName("titulo") val titulo: String,
    @SerializedName("contenido") val contenido: String,
    @SerializedName("orden") val orden: Int,
    @SerializedName("duracion_minutos") val duracionMinutos: Int
)

// ─── Request editar lección ────────────────────────────
data class UpdateLeccionRequest(
    @SerializedName("titulo") val titulo: String,
    @SerializedName("contenido") val contenido: String,
    @SerializedName("orden") val orden: Int,
    @SerializedName("duracion_minutos") val duracionMinutos: Int
)

// ─── DTO respuesta de la API ───────────────────────────
data class LeccionDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("curso_id") val cursoId: Int,
    @SerializedName("titulo") val titulo: String,
    @SerializedName("contenido") val contenido: String,
    @SerializedName("imagen_url") val imagenUrl: String?,
    @SerializedName("orden") val orden: Int,
    @SerializedName("duracion_minutos") val duracionMinutos: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

// ─── Response wrapper (create / update / delete) ───────
data class LeccionResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("curso_id") val cursoId: Int,
    @SerializedName("titulo") val titulo: String,
    @SerializedName("contenido") val contenido: String,
    @SerializedName("imagen_url") val imagenUrl: String?,
    @SerializedName("orden") val orden: Int,
    @SerializedName("duracion_minutos") val duracionMinutos: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("message") val message: String?
)