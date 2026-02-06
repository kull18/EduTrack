// di/CourseDeleteModule.kt
package com.kull18.edutrack.features.course_delete.di

import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.features.course_delete.data.repositories.CourseDeleteRepositoryImpl
import com.kull18.edutrack.features.course_delete.domain.repositories.CourseDeleteRepository
import com.kull18.edutrack.features.course_delete.domain.usecases.DeleteCourseUseCase
import com.kull18.edutrack.features.course_delete.presentation.screens.DeleteCourseViewModelFactory

class CourseDeleteModule(
    private val appContainer: AppContainer
) {

    private fun provideDeleteCourseUseCase(): DeleteCourseUseCase {
        return DeleteCourseUseCase(repository = appContainer.courseDeleteRepository)
    }

    fun provideDeleteCourseViewModelFactory(): DeleteCourseViewModelFactory {
        return DeleteCourseViewModelFactory(
            deleteCourseUseCase = provideDeleteCourseUseCase()
        )
    }
}