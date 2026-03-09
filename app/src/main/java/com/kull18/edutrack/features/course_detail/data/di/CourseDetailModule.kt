package com.kull18.edutrack.features.course_detail.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_detail.data.repositories.CourseDetailRepositoryImpl
import com.kull18.edutrack.features.course_detail.domain.repositories.CourseDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CourseDetailModule {

    @Provides
    @Singleton
    fun provideCourseDetailImpl(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): CourseDetailRepository {
        return CourseDetailRepositoryImpl(
            courseApi,
            dataStore
        )
    }
}