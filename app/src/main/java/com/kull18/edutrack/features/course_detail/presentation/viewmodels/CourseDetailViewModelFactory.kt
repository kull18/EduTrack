// viewmodels/CourseDetailViewModelFactory.kt
package com.kull18.edutrack.features.course_detail.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kull18.edutrack.features.course_detail.domain.usecases.GetCourseByIdUseCase

class CourseDetailViewModelFactory(
    private val getCourseByIdUseCase: GetCourseByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseDetailViewModel(getCourseByIdUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}