package com.kull18.edutrack.core.di

import android.content.Context
import com.kull18.edutrack.BuildConfig
import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.core.network.LoginApi
import com.kull18.edutrack.core.network.RegisterApi
import com.kull18.edutrack.features.courses_list_instructor.data.repositories.CourseRepositoryImpl
import com.kull18.edutrack.features.courses_list_instructor.domain.repositories.CourseRepository
import com.kull18.edutrack.features.login.data.repositories.LoginRepositoryImpl
import com.kull18.edutrack.features.login.domain.repositories.LoginRepository
import com.kull18.edutrack.features.register.data.repositories.RegisterRepositoryImpl
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {
    private val retrofit: Retrofit by lazy {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client) // 🔹 Aquí agregamos el client con logging
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val tokenDataStore = TokenDataStore(context)


    val courseApi: CourseApi  by lazy {
        retrofit.create(CourseApi::class.java)
    }

    val loginApi: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }

    val registerApi: RegisterApi by lazy {
        retrofit.create(RegisterApi::class.java)
    }

    val courseRepository: CourseRepository by lazy {
        CourseRepositoryImpl(courseApi, tokenDataStore)
    }

    val loginRepository: LoginRepositoryImpl by lazy {
        LoginRepositoryImpl(loginApi, tokenDataStore)
    }

    val registerRepository: RegisterRepositoryImpl by lazy {
        RegisterRepositoryImpl(registerApi, tokenDataStore)
    }
}