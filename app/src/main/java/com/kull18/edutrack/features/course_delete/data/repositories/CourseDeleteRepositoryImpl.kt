package com.kull18.edutrack.features.course_delete.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_delete.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.course_delete.domain.entities.CourseDelete
import com.kull18.edutrack.features.course_delete.domain.repositories.CourseDeleteRepository
import kotlinx.coroutines.flow.first

class CourseDeleteRepositoryImpl(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : CourseDeleteRepository {

    override suspend fun deleteCourse(id: Int): CourseDelete {
        val token = tokenDataStore.getToken().first()
        val response = api.deleteCourse("Bearer $token", id)
        return response.toDomain()
    }
}