package com.kull18.edutrack.features.login.data.repositories

import com.kull18.edutrack.core.datastore.TokenDataStore
import com.kull18.edutrack.core.network.CourseApi
import com.kull18.edutrack.features.login.data.datasources.mapper.toDomain
import com.kull18.edutrack.features.login.data.datasources.models.LoginRequest
import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse
import com.kull18.edutrack.features.login.domain.entities.User
import com.kull18.edutrack.features.login.domain.repositories.LoginRepository


class LoginRepositoryImpl(
    private val api: CourseApi,
    private val tokenDataStore: TokenDataStore
) : LoginRepository {

    override suspend fun login(user: LoginRequest): User {
        val response = api.loginUser(user)
        tokenDataStore.saveToken(response.token)
        val userResponse = response.user.toDomain()
        tokenDataStore.saveUser(userResponse)
        return userResponse
    }
}
