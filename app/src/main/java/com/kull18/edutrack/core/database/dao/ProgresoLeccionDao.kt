package com.kull18.edutrack.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kull18.edutrack.core.database.entities.ProgresoLeccionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgresoLeccionDao {

    // Obtener todas las lecciones de una inscripción específica
    @Query("SELECT * FROM progreso_lecciones WHERE inscripcionId = :inscripcionId ORDER BY leccionId ASC")
    fun getProgresoByInscripcion(inscripcionId: Int): Flow<List<ProgresoLeccionEntity>>

    // Contar cuántas lecciones están completadas en una inscripción
    @Query("SELECT COUNT(*) FROM progreso_lecciones WHERE inscripcionId = :inscripcionId AND completada = 1")
    suspend fun countCompletadas(inscripcionId: Int): Int

    // Contar el total de lecciones en una inscripción
    @Query("SELECT COUNT(*) FROM progreso_lecciones WHERE inscripcionId = :inscripcionId")
    suspend fun countTotal(inscripcionId: Int): Int

    // Guardar/sincronizar lecciones desde la API
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgreso(lecciones: List<ProgresoLeccionEntity>)

    // Marcar una lección como completada localmente
    @Query("UPDATE progreso_lecciones SET completada = :completada, fechaCompletado = :fecha WHERE leccionId = :leccionId")
    suspend fun updateCompletada(leccionId: Int, completada: Boolean, fecha: String?)

    // Limpiar progreso de una inscripción (útil al hacer logout)
    @Query("DELETE FROM progreso_lecciones WHERE inscripcionId = :inscripcionId")
    suspend fun clearProgresoByInscripcion(inscripcionId: Int)
}