package com.kull18.edutrack.features.course_registration.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_registration.data.repositories.CourseRegistrationRepositoryImpl
import com.kull18.edutrack.features.course_registration.domain.repositories.CourseRegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CourseRegistrationModule {

    @Provides
    @Singleton
    fun provideCourseRegistrationRepository(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): CourseRegistrationRepository {
        return CourseRegistrationRepositoryImpl(
            api = courseApi,
            tokenDataStore = dataStore
        )
    }
}

