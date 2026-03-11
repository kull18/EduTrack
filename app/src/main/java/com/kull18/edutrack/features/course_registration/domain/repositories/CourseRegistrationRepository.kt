package com.kull18.edutrack.features.course_registration.domain.repositories

import com.kull18.edutrack.features.course_registration.data.datasources.models.ProgresoResponse
import com.kull18.edutrack.features.course_registration.domain.entities.Inscripcion
import com.kull18.edutrack.features.course_registration.domain.entities.RegistrationCourse

interface CourseRegistrationRepository {
    suspend fun getAvailableCourses(): List<RegistrationCourse>
    suspend fun enrollInCourse(courseId: Int): String
    suspend fun getMisInscripciones(): List<Inscripcion>
    suspend fun getInscripcionById(inscripcionId: Int): Inscripcion
    suspend fun updateProgreso(inscripcionId: Int, leccionId: Int, completada: Boolean): ProgresoResponse
    suspend fun getProgreso(inscripcionId: Int): ProgresoResponse
}

