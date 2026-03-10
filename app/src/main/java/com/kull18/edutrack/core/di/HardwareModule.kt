package com.kull18.edutrack.core.hardware.di

import com.kull18.edutrack.core.hardware.data.AndroidCamaraManager
import com.kull18.edutrack.core.hardware.data.AndroidGaleriaManager
import com.kull18.edutrack.core.hardware.data.AndroidNotificacionManager
import com.kull18.edutrack.core.hardware.domain.CamaraManager
import com.kull18.edutrack.core.hardware.domain.GaleriaManager
import com.kull18.edutrack.core.hardware.domain.NotificacionManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HardwareModule {

    @Binds
    @Singleton
    abstract fun bindCamaraManager(
        impl: AndroidCamaraManager
    ): CamaraManager

    @Binds
    @Singleton
    abstract fun bindGaleriaManager(
        impl: AndroidGaleriaManager
    ): GaleriaManager

    @Binds
    @Singleton
    abstract fun bindNotificacionManager(
        impl: AndroidNotificacionManager
    ): NotificacionManager
}