package com.kull18.edutrack.features.login.data.datasources.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.kull18.edutrack.features.login.domain.entities.UserRole

data class LoginResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("usuario") // <-- cambió de "user" a "usuario"
    val user: LoginDTO
) : Serializable

data class LoginDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("rol")
    val rol: String,

    @SerializedName("created_at") // <-- coincidencia con JSON
    val createdAt: String,

    @SerializedName("updated_at") // <-- coincidencia con JSON
    val updatedAt: String
) : Serializable
