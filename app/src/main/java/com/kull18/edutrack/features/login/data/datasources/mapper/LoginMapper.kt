package com.kull18.edutrack.features.login.data.datasources.mapper

import com.kull18.edutrack.features.login.data.datasources.models.LoginDTO
import com.kull18.edutrack.features.login.domain.entities.User

fun LoginDTO.toDomain(): User {
    return User(
        id = this.id,
        nombre = this.nombre,
        email = this.email,
        rol = this.rol,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
