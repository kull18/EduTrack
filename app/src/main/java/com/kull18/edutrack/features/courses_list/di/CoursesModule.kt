package com.kull18.edutrack.features.courses_list.di

import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.features.courses_list.domain.usecases.GetAllCoursesUseCase
import com.kull18.edutrack.features.courses_list.presentation.viewmodels.CourseListViewModelFactory

class CourseModule(
    private val appContainer: AppContainer
) {

    private fun provideGetAllCoursesUseCase(): GetAllCoursesUseCase {
        return GetAllCoursesUseCase(repository = appContainer.courseRepository)
    }

    fun provideCourseListViewModelFactory(): CourseListViewModelFactory {
        return CourseListViewModelFactory(
            getAllCoursesUseCase = provideGetAllCoursesUseCase()
        )
    }
}
