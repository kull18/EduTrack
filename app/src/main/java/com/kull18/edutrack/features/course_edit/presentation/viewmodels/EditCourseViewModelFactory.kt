// viewmodels/EditCourseViewModelFactory.kt
package com.kull18.edutrack.features.course_edit.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kull18.edutrack.features.course_edit.domain.usecases.GetCourseByIdUseCase
import com.kull18.edutrack.features.course_edit.domain.usecases.UpdateCourseUseCase

class EditCourseViewModelFactory(
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditCourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditCourseViewModel(getCourseByIdUseCase, updateCourseUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}