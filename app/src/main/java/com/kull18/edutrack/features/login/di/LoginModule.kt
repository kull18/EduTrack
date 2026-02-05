package com.kull18.edutrack.features.auth.di

import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.features.auth.domain.usecases.LoginUseCase
import com.kull18.edutrack.features.auth.presentation.viewmodels.LoginViewModelFactory

class LoginModule(
    private val appContainer: AppContainer
) {

    private fun provideLoginUseCase(): LoginUseCase {
        return LoginUseCase(
            repository = appContainer.loginRepository
        )
    }

    fun provideLoginViewModelFactory(): LoginViewModelFactory {
        return LoginViewModelFactory(
            loginUseCase = provideLoginUseCase()
        )
    }
}
