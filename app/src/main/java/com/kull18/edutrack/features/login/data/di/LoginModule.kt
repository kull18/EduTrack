package com.kull18.edutrack.features.login.data.di

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.login.data.repositories.LoginRepositoryImpl
import com.kull18.edutrack.features.login.domain.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginImpl(
        courseApi: CourseApi,
        dataStore: TokenDataStore
    ): LoginRepository {
        return LoginRepositoryImpl(
            courseApi,
            dataStore
        )
    }
}