package com.kull18.edutrack.features.register.data.di

import androidx.datastore.core.DataStore
import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.register.data.repositories.RegisterRepositoryImpl
import com.kull18.edutrack.features.register.domain.repositories.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RegisterModule {

    @Provides
    @Singleton
    fun provideRegisterImpl(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): RegisterRepository {
        return RegisterRepositoryImpl(
            courseApi,
            dataStore
        )
    }
}