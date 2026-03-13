package com.kull18.edutrack.features.course_registration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.kull18.edutrack.core.hardware.domain.NotificacionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnrollmentConfirmationViewModel @Inject constructor(
    private val notificacionManager: NotificacionManager
) : ViewModel() {

    private val notifiedCourseIds = mutableSetOf<Int>()

    fun notifyEnrollmentIfNeeded(courseId: Int, courseName: String) {
        if (!notifiedCourseIds.add(courseId)) return

        val title = "Inscripcion confirmada"
        val message = "Ya estas inscrito en: $courseName"
        notificacionManager.mostrarNotificacion(title, message)
    }
}

