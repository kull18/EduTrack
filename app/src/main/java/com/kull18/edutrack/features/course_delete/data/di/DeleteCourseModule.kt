package com.kull18.edutrack.features.course_delete.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_delete.data.repositories.CourseDeleteRepositoryImpl
import com.kull18.edutrack.features.course_delete.domain.repositories.CourseDeleteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DeleteCourseModule {

    @Provides
    @Singleton
    fun provvideDeleteCourseImpl(
        courseRepository: CourseApi,
        dataStore: TokenDataStore
    ): CourseDeleteRepository {
        return CourseDeleteRepositoryImpl(
            courseRepository,
            dataStore
        )
    }
}