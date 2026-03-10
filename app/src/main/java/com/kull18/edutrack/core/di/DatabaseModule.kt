package com.kull18.edutrack.core.di

import android.content.Context
import androidx.room.Room
import com.kull18.edutrack.core.database.AppDatabase
import com.kull18.edutrack.core.database.dao.CursoDao
import com.kull18.edutrack.core.database.dao.ProgresoLeccionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "EduTrackDB"
        ).build()
    }

    // Cada feature pide solo el DAO que necesita
    @Provides
    fun provideCursoDao(db: AppDatabase): CursoDao = db.cursoDao()

    @Provides
    fun provideProgresoLeccionDao(db: AppDatabase): ProgresoLeccionDao = db.progresoLeccionDao()
}