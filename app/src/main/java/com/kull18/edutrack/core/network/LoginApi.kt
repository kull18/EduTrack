package com.kull18.edutrack.core.network

import com.kull18.edutrack.features.login.data.datasources.models.LoginRequest
import com.kull18.edutrack.features.login.data.datasources.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse

}