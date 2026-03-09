package com.kull18.edutrack.core.di

import android.content.Context
import com.kull18.edutrack.BuildConfig
import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_create.data.repositories.CreateCourseRepositoryImpl
import com.kull18.edutrack.features.course_create.domain.repositories.CreateCourseRepository
import com.kull18.edutrack.features.course_delete.data.repositories.CourseDeleteRepositoryImpl
import com.kull18.edutrack.features.course_detail.data.repositories.CourseDetailRepositoryImpl
import com.kull18.edutrack.features.course_detail.domain.repositories.CourseDetailRepository
import com.kull18.edutrack.features.course_edit.data.repositories.CourseEditRepositoryImpl
import com.kull18.edutrack.features.course_edit.domain.entities.Course
import com.kull18.edutrack.features.courses_list.data.repositories.CourseRepositoryImpl
import com.kull18.edutrack.features.courses_list.domain.repositories.CourseRepository
import com.kull18.edutrack.features.login.data.repositories.LoginRepositoryImpl
import com.kull18.edutrack.features.register.data.repositories.RegisterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppContainer() {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): TokenDataStore {
        return TokenDataStore(context)
    }

    @Singleton
    @Provides
    fun provideCourseApi(retrofit: Retrofit): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }
}