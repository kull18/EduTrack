package com.kull18.edutrack.features.lesson.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.lesson.data.repositories.LeccionRepositoryImpl
import com.kull18.edutrack.features.lesson.domain.repositories.LeccionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LeccionModule {

    @Provides
    @Singleton
    fun provideLeccionRepository(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): LeccionRepository {
        return LeccionRepositoryImpl(courseApi, dataStore)
    }
}