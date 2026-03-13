package com.kull18.edutrack.features.course_registration.domain.repositories

import com.kull18.edutrack.features.course_registration.domain.entities.CursoProgreso
import kotlinx.coroutines.flow.Flow

interface MyCourseRepository {
    fun getProgreso(courseId: Int): Flow<CursoProgreso>
    suspend fun toggleLeccion(leccionId: Int, completada: Boolean)
}