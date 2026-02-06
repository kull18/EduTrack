package com.kull18.edutrack.features.register.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.course_edit.domain.entities.Course
import com.kull18.edutrack.features.login.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.register.domain.entities.User
import com.kull18.edutrack.features.register.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.register.data.datasources.models.RegisterRequest
import com.kull18.edutrack.features.register.domain.repositories.RegisterRepository

class RegisterRepositoryImpl(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : RegisterRepository {

    override suspend fun registerUser(user: RegisterRequest): User {
        val response = api.registerUser(user)

        if (response.message.isNotEmpty()) {
            tokenDataStore.saveToken(response.token)
        }

        return response.user.toDomain()
    }
}
