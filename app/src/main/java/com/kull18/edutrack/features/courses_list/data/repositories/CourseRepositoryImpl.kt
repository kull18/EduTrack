package com.kull18.edutrack.features.courses_list.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.courses_list.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.courses_list.domain.entities.Course
import com.kull18.edutrack.features.courses_list.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.first

class CourseRepositoryImpl(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : CourseRepository {

    override suspend fun getAllCourses(): List<Course> {
        val token = tokenDataStore.getToken().first()  // Obtener token correctamente
        return api.getAllCourses("Bearer $token").map { it.toDomain() }
    }

}
