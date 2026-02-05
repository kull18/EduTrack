package com.kull18.edutrack.features.register.domain.entities

data class User(
    val id: Int,
    val nombre: String,
    val email: String,
    val rol: String,
    val createdAt: String,
    val updatedAt: String
)
