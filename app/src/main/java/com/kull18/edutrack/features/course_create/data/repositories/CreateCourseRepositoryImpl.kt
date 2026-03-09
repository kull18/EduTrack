package com.kull18.edutrack.features.course_create.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_create.domain.entities.CourseCreate
import com.kull18.edutrack.features.course_create.domain.repositories.CreateCourseRepository
import com.kull18.edutrack.features.course_create.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.course_create.data.datasources.models.CourseActionRequest
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CreateCourseRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : CreateCourseRepository {

    override suspend fun createCourse(course: CourseActionRequest): CourseCreate {
        val token = tokenDataStore.getToken().first() // Agregar .first() aquí
        val user = tokenDataStore.getUser().first()
        course.instructorId = user?.id ?: throw Exception("Usuario no encontrado")
        val response = api.createCourse("Bearer $token", course) // Agregar "Bearer " si es necesario

        return response.curso.toDomain()
    }
}
