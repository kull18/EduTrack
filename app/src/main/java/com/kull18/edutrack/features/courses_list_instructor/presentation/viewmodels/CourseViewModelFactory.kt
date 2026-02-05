package com.kull18.edutrack.features.courses_list_instructor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kull18.edutrack.features.courses_list_instructor.domain.usecases.GetAllCoursesUseCase

class CourseListViewModelFactory(
    private val getAllCoursesUseCase: GetAllCoursesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseListViewModel(getAllCoursesUseCase) as T
        }
        throw IllegalArgumentException("ViewModel desconocido: ${modelClass.name}")
    }
}
