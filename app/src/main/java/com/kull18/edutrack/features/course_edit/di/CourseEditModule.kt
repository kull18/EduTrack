// di/CourseEditModule.kt
package com.kull18.edutrack.features.course_edit.di

import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.features.course_edit.data.repositories.CourseEditRepositoryImpl
import com.kull18.edutrack.features.course_edit.domain.repositories.CourseEditRepository
import com.kull18.edutrack.features.course_edit.domain.usecases.GetCourseByIdUseCase
import com.kull18.edutrack.features.course_edit.domain.usecases.UpdateCourseUseCase
import com.kull18.edutrack.features.course_edit.presentation.screens.EditCourseViewModelFactory

class CourseEditModule(
    private val appContainer: AppContainer
) {

    private fun provideGetCourseByIdUseCase(): GetCourseByIdUseCase {
        return GetCourseByIdUseCase(repository = appContainer.courseEditRepository)
    }

    private fun provideUpdateCourseUseCase(): UpdateCourseUseCase {
        return UpdateCourseUseCase(repository = appContainer.courseEditRepository)
    }

    fun provideEditCourseViewModelFactory(): EditCourseViewModelFactory {
        return EditCourseViewModelFactory(
            getCourseByIdUseCase = provideGetCourseByIdUseCase(),
            updateCourseUseCase = provideUpdateCourseUseCase()
        )
    }
}