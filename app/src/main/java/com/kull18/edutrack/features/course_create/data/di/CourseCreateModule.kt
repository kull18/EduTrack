package com.kull18.edutrack.features.course_create.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_create.data.repositories.CreateCourseRepositoryImpl
import com.kull18.edutrack.features.course_create.domain.repositories.CreateCourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CourseCreateModule {

    @Provides
    @Singleton
    fun provideCreaateCourse(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): CreateCourseRepository {
        return CreateCourseRepositoryImpl(
            courseApi,
            dataStore
        )
    }
}