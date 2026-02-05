package com.example.cursosapp.ui.components.organisms.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import com.kull18.edutrack.features.login.presentation.components.atoms.VerticalSpacer
import com.kull18.edutrack.features.login.presentation.components.molecules.EmailTextField
import com.kull18.edutrack.features.login.presentation.components.molecules.PasswordTextField

@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    emailError: String? = null,
    passwordError: String? = null,
    enabled: Boolean = true,
    onSubmit: () -> Unit = {},
    onChangeVisible: (Boolean) -> Unit,
    passwordVisible: Boolean
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.fillMaxWidth()) {

        EmailTextField(
            value = email,
            onValueChange = onEmailChange,
            enabled = enabled,
            isError = emailError != null,
            errorMessage = emailError,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Down)
            },
            leadingIcon = Icons.Default.Email
        )

        VerticalSpacer(16)

        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            enabled = enabled,
            isError = passwordError != null,
            errorMessage = passwordError,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                onSubmit()
            },
            leadingIcon = Icons.Default.Lock,
            showPasswordToggle = true,
            onChangeVisible = onChangeVisible,
            passwordVisible = passwordVisible
        )
    }
}
