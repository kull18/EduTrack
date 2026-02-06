package com.kull18.edutrack.features.course_create.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kull18.edutrack.features.course_create.domain.usecases.CreateCourseUseCase

class CreateCourseViewModelFactory(
    private val createCourseUseCase: CreateCourseUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateCourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateCourseViewModel(createCourseUseCase) as T
        }
        throw IllegalArgumentException("ViewModel desconocido: ${modelClass.name}")
    }
}
