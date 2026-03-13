package com.kull18.edutrack.features.course_registration.data.di

import com.kull18.edutrack.core.database.dao.CursoDao
import com.kull18.edutrack.core.database.dao.ProgresoLeccionDao
import com.kull18.edutrack.features.course_registration.data.repositories.MyCourseRepositoryImpl
import com.kull18.edutrack.features.course_registration.domain.repositories.MyCourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyCourseModule {

    @Provides
    @Singleton
    fun provideMyCourseRepository(
        progresoDao: ProgresoLeccionDao,
        cursoDao: CursoDao
    ): MyCourseRepository {
        return MyCourseRepositoryImpl(
            progresoDao = progresoDao,
            cursoDao = cursoDao
        )
    }
}