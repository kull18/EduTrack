package com.kull18.edutrack.core.di

import com.kull18.edutrack.core.hardware.data.AndroidCamaraManager
import com.kull18.edutrack.core.hardware.domain.CamaraManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CameraModule {

    @Binds
    @Singleton
    abstract fun bindCamaraManager(
        androidCamaraManager: AndroidCamaraManager
    ): CamaraManager
}