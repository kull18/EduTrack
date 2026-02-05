package com.kull18.edutrack.features.register.domain.repositories

import com.kull18.edutrack.features.register.domain.entities.User
import com.kull18.edutrack.features.register.data.datasources.models.RegisterRequest

interface RegisterRepository {

    suspend fun registerUser(user: RegisterRequest): User
}