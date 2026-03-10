package com.kull18.edutrack.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cursos_cache")
data class CursoEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val descripcion: String,
    val duracion: String,
    val instructorNombre: String,
    val categoria: String,
    val imagenUrl: String?,
    val rating: Float,
    val estado: String,
    val estaInscrito: Boolean = false
)