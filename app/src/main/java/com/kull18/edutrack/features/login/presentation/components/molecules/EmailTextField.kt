package com.kull18.edutrack.features.login.presentation.components.molecules

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: ImageVector
) {
    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Correo electrónico",
        leadingIcon = leadingIcon,
        modifier = modifier.fillMaxWidth(),
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        keyboardActions = keyboardActions,
        enabled = enabled,
        isError = isError,
        errorMessage = errorMessage
    )
}
