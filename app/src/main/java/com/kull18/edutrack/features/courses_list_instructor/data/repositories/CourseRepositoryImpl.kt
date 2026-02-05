package com.kull18.edutrack.features.courses_list_instructor.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.courses_list_instructor.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.courses_list_instructor.domain.entities.Course
import com.kull18.edutrack.features.courses_list_instructor.domain.repositories.CourseRepository

class CourseRepositoryImpl(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : CourseRepository {

    override suspend fun getAllCourses(): List<Course> {
        val token: String = tokenDataStore.getToken().toString()

        return api.getAllCourses(token).map { it.toDomain() }
    }
}
