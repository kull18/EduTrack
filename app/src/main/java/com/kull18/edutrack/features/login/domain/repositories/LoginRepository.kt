package com.kull18.edutrack.features.login.domain.repositories

import com.kull18.edutrack.features.login.data.datasources.models.LoginRequest
import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse
import com.kull18.edutrack.features.login.domain.entities.User

interface LoginRepository {
    suspend fun login(user: LoginRequest): User
}