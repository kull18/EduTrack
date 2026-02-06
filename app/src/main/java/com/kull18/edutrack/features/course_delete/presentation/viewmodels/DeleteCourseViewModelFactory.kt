package com.kull18.edutrack.features.course_delete.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kull18.edutrack.features.course_delete.domain.usecases.DeleteCourseUseCase

class DeleteCourseViewModelFactory(
    private val deleteCourseUseCase: DeleteCourseUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeleteCourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeleteCourseViewModel(deleteCourseUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}