package com.kull18.edutrack.features.register.data.datasources.models


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("token")
    val token: String,

    @SerializedName("usuario")
    val user: RegisterDTO
)

data class RegisterDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("rol")
    val rol: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
)