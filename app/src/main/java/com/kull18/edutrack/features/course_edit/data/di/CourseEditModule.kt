package com.kull18.edutrack.features.course_edit.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_edit.data.repositories.CourseEditRepositoryImpl
import com.kull18.edutrack.features.course_edit.domain.repositories.CourseEditRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CourseEditModule {

    @Provides
    @Singleton
    fun provideCourseEditImpl(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): CourseEditRepository {
        return CourseEditRepositoryImpl(
            courseApi,
            dataStore
        )
    }
}