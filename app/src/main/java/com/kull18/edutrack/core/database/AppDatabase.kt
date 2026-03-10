package com.kull18.edutrack.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kull18.edutrack.core.database.dao.CursoDao
import com.kull18.edutrack.core.database.dao.ProgresoLeccionDao
import com.kull18.edutrack.core.database.entities.CursoEntity
import com.kull18.edutrack.core.database.entities.ProgresoLeccionEntity

@Database(
    entities = [
        CursoEntity::class,
        ProgresoLeccionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cursoDao(): CursoDao
    abstract fun progresoLeccionDao(): ProgresoLeccionDao
}