package com.kull18.edutrack.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progreso_lecciones")
data class ProgresoLeccionEntity(
    @PrimaryKey val leccionId: Int,
    val inscripcionId: Int,
    val titulo: String,
    val completada: Boolean = false,
    val fechaCompletado: String? = null   // ISO 8601: "2024-03-09T10:30:00"
)