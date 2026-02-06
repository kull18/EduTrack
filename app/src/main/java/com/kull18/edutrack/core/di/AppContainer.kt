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
import com.kull18.edutrack.features.courses_list.data.repositories.CourseRepositoryImpl
import com.kull18.edutrack.features.courses_list.domain.repositories.CourseRepository
import com.kull18.edutrack.features.login.data.repositories.LoginRepositoryImpl
import com.kull18.edutrack.features.register.data.repositories.RegisterRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val tokenDataStore = TokenDataStore(context)


    val courseApi: CourseApi  by lazy {
        retrofit.create(CourseApi::class.java)
    }

    val courseRepository: CourseRepository by lazy {
        CourseRepositoryImpl(courseApi, tokenDataStore)
    }

    val createCourseRepository: CreateCourseRepository by lazy {
        CreateCourseRepositoryImpl(courseApi, tokenDataStore)
    }

    val courseDetailRepository: CourseDetailRepository by lazy {
        CourseDetailRepositoryImpl(courseApi, tokenDataStore)
    }

    val courseEditRepository: CourseEditRepositoryImpl by lazy {
        CourseEditRepositoryImpl(courseApi,tokenDataStore)
    }

    val courseDeleteRepository: CourseDeleteRepositoryImpl by lazy {
        CourseDeleteRepositoryImpl(courseApi, tokenDataStore)
    }

    val loginRepository: LoginRepositoryImpl by lazy {
        LoginRepositoryImpl(courseApi, tokenDataStore)
    }

    val registerRepository: RegisterRepositoryImpl by lazy {
        RegisterRepositoryImpl(courseApi, tokenDataStore)
    }
}