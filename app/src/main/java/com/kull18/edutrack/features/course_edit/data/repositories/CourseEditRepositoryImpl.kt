// data/repositories/CourseEditRepositoryImpl.kt
package com.kull18.edutrack.features.course_edit.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_edit.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.course_edit.data.datasources.models.CourseEditRequest
import com.kull18.edutrack.features.course_edit.domain.entities.Course
import com.kull18.edutrack.features.course_edit.domain.entities.CourseEdit
import com.kull18.edutrack.features.course_edit.domain.repositories.CourseEditRepository
import kotlinx.coroutines.flow.first

class CourseEditRepositoryImpl(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : CourseEditRepository {

    override suspend fun getCourseById(id: Int): Course {
        val token = tokenDataStore.getToken().first()
        return api.getCourseById("Bearer $token", id).toDomain()
    }

    override suspend fun updateCourse(id: Int, course: CourseEditRequest): CourseEdit {
        val token = tokenDataStore.getToken().first()
        return api.updateCourse("Bearer $token", id, course).curso.toDomain()
    }
}