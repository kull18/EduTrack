package com.kull18.edutrack.features.course_detail.di

import android.app.Application
import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.di.AppContainer
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_detail.data.repositories.CourseDetailRepositoryImpl
import com.kull18.edutrack.features.course_detail.domain.repositories.CourseDetailRepository
import com.kull18.edutrack.features.course_detail.domain.usecases.GetCourseByIdUseCase
import com.kull18.edutrack.features.course_detail.presentation.screens.CourseDetailViewModelFactory

class CourseDetailModule(
    val application: AppContainer
) {

    fun provideGetCourseByIdUseCase(): GetCourseByIdUseCase {
        return GetCourseByIdUseCase(application.courseDetailRepository)
    }

    fun provideCourseDetailViewModelFactory(): CourseDetailViewModelFactory {
        return CourseDetailViewModelFactory(provideGetCourseByIdUseCase())
    }
}