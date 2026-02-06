package com.kull18.edutrack.features.course_detail.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_detail.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.course_detail.domain.entities.CourseDetial
import com.kull18.edutrack.features.course_detail.domain.repositories.CourseDetailRepository
import com.kull18.edutrack.features.course_edit.data.datasources.mapper.toDomain
import kotlinx.coroutines.flow.first

class CourseDetailRepositoryImpl(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : CourseDetailRepository {

    override suspend fun getCourseByIdDetail(id: Int): CourseDetial {
        val token = tokenDataStore.getToken().first()
        return api.getCourseByIdDetail("Bearer $token", id).toDomain()
    }
}