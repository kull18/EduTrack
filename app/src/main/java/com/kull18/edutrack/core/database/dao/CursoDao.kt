package com.kull18.edutrack.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kull18.edutrack.core.database.entities.CursoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CursoDao {

    // Obtener todos los cursos del caché (para modo offline)
    @Query("SELECT * FROM cursos_cache")
    fun getAllCursos(): Flow<List<CursoEntity>>

    // Obtener solo los cursos en los que el alumno está inscrito
    @Query("SELECT * FROM cursos_cache WHERE estaInscrito = 1")
    fun getCursosInscritos(): Flow<List<CursoEntity>>

    // Guardar/actualizar cursos desde la API
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCursos(cursos: List<CursoEntity>)

    // Marcar un curso como inscrito localmente (antes de confirmar con API)
    @Query("UPDATE cursos_cache SET estaInscrito = 1 WHERE id = :cursoId")
    suspend fun marcarInscrito(cursoId: Int)

    // Limpiar caché (útil al hacer logout)
    @Query("DELETE FROM cursos_cache")
    suspend fun clearCursos()
}