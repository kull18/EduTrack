package com.kull18.edutrack.features.courses_list.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.courses_list.data.repositories.CourseRepositoryImpl
import com.kull18.edutrack.features.courses_list.domain.repositories.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CoursesListModule {
    @Provides
    @Singleton
    fun provideCoursesList(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): CourseRepository {
        return CourseRepositoryImpl(
            courseApi,
            dataStore
        )
    }
}