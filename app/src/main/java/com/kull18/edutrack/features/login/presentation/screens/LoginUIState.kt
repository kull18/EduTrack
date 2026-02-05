package com.kull18.edutrack.features.auth.presentation.viewmodels

import com.kull18.edutrack.features.login.domain.entities.User


data class LoginUIState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val isLoggedIn: Boolean = false,
    val error: String? = null
)
