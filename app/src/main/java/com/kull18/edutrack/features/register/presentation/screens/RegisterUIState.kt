package com.kull18.edutrack.features.register.presentation.screens

import com.kull18.edutrack.features.register.domain.entities.User

data class RegisterUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null,
    val isRegistered: Boolean = false
)
