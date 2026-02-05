package com.kull18.edutrack.features.courses_list_instructor.di

import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.features.courses_list_instructor.domain.usecases.GetAllCoursesUseCase
import com.kull18.edutrack.features.courses_list_instructor.presentation.viewmodels.CourseListViewModelFactory
import com.kull18.edutrack.features.courses_list_instructor.data.repositories.CourseRepositoryImpl
import com.kull18.edutrack.features.courses_list_instructor.domain.repositories.CourseRepository

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
