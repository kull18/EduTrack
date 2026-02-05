package com.kull18.edutrack.core.network

import com.kull18.edutrack.features.login.domain.entities.User
import com.kull18.edutrack.features.register.data.datasources.models.RegisterRequest
import com.kull18.edutrack.features.register.data.datasources.models.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("api/auth/register")
    suspend fun registerUser(
        @Body user: RegisterRequest
    ): RegisterResponse

}