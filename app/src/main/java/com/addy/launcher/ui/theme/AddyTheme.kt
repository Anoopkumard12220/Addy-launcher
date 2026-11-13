package com.addy.launcher.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.addy.launcher.R

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF08090B),
    surface = Color(0xFF121316),
    surfaceContainerHighest = Color(0xFF1B1C20),
    primary = Color(0xFFD7D7DC),
    onPrimary = Color(0xFF08090B),
    primaryContainer = Color(0xFF1B1C20),
    onPrimaryContainer = Color(0xFFD7D7DC),
    secondary = Color(0xFF92939A),
    onSecondary = Color(0xFF08090B),
    surfaceVariant = Color(0xFF292A30),
    onSurfaceVariant = Color(0xFF92939A),
    outline = Color(0xFF292A30),
    onSurface = Color(0xFFFFFFFF),
    inverseSurface = Color(0xFF121316),
    inverseOnSurface = Color(0xFFFFFFFF),
    inversePrimary = Color(0xFF686970)
)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFF08090B),
    surface = Color(0xFF121316),
    primary = Color(0xFFD7D7DC),
    onPrimary = Color(0xFF08090B)
)

@Composable
fun AddyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    CompositionLocalProvider(
        androidx.compose.material3.LocalContentColor provides colorScheme.onSurface
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
