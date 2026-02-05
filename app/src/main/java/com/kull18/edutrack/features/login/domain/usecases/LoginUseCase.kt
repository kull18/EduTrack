package com.kull18.edutrack.features.auth.domain.usecases

import com.kull18.edutrack.features.login.data.datasources.models.LoginRequest
import com.kull18.edutrack.features.login.domain.entities.User
import com.kull18.edutrack.features.login.domain.repositories.LoginRepository

class LoginUseCase(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(
        loginRequest: LoginRequest
    ): Result<User> {
        return try {
            val user = repository.login(loginRequest)

            if (user.email.isBlank()) {
                Result.failure(Exception("Credenciales inválidas"))
            } else {
                Result.success(user)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
