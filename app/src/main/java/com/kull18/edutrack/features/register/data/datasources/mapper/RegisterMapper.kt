package com.kull18.edutrack.features.register.data.datasources.mapper

import com.kull18.edutrack.features.register.data.datasources.models.RegisterDTO
import com.kull18.edutrack.features.register.domain.entities.User

fun RegisterDTO.toDomain(): User {
    return User(
        id = this.id,
        nombre = this.nombre,
        email = this.email,
        rol = this.rol,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
