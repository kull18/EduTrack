package com.kull18.edutrack.features.register.di

import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.features.auth.domain.usecases.RegisterUseCase
import com.kull18.edutrack.features.auth.presentation.viewmodels.RegisterViewModelFactory

class RegisterModule(
    private val appContainer: AppContainer
) {

    private fun provideRegisterUseCase(): RegisterUseCase {
        return RegisterUseCase(
            repository = appContainer.registerRepository
        )
    }

    fun provideRegisterViewModelFactory(): RegisterViewModelFactory {
        return RegisterViewModelFactory(
            registerUseCase = provideRegisterUseCase()
        )
    }
}
