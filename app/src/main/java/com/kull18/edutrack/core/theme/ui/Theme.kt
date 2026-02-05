package com.kull18.edutrack.core.theme.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary500,
    onPrimary = White,

    secondary = Primary600,
    onSecondary = White,

    // ✅ usamos tertiary para success
    tertiary = Success500,
    onTertiary = White,

    background = White,
    onBackground = Neutral900,

    surface = White,
    onSurface = Neutral900,

    // ✅ textos secundarios / placeholders
    surfaceVariant = Neutral200,
    onSurfaceVariant = Neutral600,

    error = Error500,
    onError = White,

    outline = Neutral200
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary500,
    onPrimary = White,

    secondary = Primary600,
    onSecondary = White,

    tertiary = Success500,
    onTertiary = White,

    background = Neutral900,
    onBackground = White,

    surface = Neutral900,
    onSurface = White,

    surfaceVariant = Neutral400,
    onSurfaceVariant = Neutral400,

    error = Error500,
    onError = White,

    outline = Neutral400
)

@Composable
fun EduTrackTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
