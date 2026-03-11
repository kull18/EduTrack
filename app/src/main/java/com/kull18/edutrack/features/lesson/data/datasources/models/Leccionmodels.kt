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

// ─── DTO respuesta GET (lista / detalle) ───────────────
// El backend devuelve directamente el objeto
data class LeccionDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("curso_id") val cursoId: Int,
    @SerializedName("titulo") val titulo: String,
    @SerializedName("contenido") val contenido: String?,
    @SerializedName("imagen_url") val imagenUrl: String?,
    @SerializedName("orden") val orden: Int,
    @SerializedName("duracion_minutos") val duracionMinutos: Int,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?   // nullable — Go no siempre lo devuelve
)

// ─── Response CREATE/UPDATE/DELETE ────────────────────
// El backend devuelve: { "message": "...", "leccion": { ... } }
data class LeccionResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("leccion") val leccion: LeccionDTO?   // ← anidado aquí
)

// Respuesta simple solo con message (delete)
data class MessageResponse(
    @SerializedName("message") val message: String?
)