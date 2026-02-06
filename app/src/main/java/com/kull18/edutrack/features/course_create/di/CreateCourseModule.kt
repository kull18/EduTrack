package com.kull18.edutrack.features.course_create.di

import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.features.course_create.domain.usecases.CreateCourseUseCase
import com.kull18.edutrack.features.course_create.presentation.viewmodels.CreateCourseViewModelFactory

class CreateCourseModule(
    private val appContainer: AppContainer
) {

    // Provee el caso de uso
    private fun provideCreateCourseUseCase(): CreateCourseUseCase {
        return CreateCourseUseCase(
            repository = appContainer.createCourseRepository
        )
    }

    // Provee el ViewModelFactory
    fun provideCreateCourseViewModelFactory(): CreateCourseViewModelFactory {
        return CreateCourseViewModelFactory(
            createCourseUseCase = provideCreateCourseUseCase()
        )
    }
}
