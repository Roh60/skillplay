package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = SaffronPrimary,
    onPrimary = Color.White,
    primaryContainer = SaffronDark,
    onPrimaryContainer = SaffronLight,
    secondary = ChakraBlue,
    onSecondary = Color.White,
    secondaryContainer = DeepNavySurface,
    onSecondaryContainer = ChakraBlueLight,
    tertiary = IndiaGreen,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFF064E3B),
    onTertiaryContainer = IndiaGreenLight,
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = BorderDark,
    error = ErrorRed,
    errorContainer = Color(0xFF7F1D1D),
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = SaffronPrimary,
    onPrimary = Color.White,
    primaryContainer = SaffronLight,
    onPrimaryContainer = SaffronDark,
    secondary = ChakraBlue,
    onSecondary = Color.White,
    secondaryContainer = ChakraBlueLight,
    onSecondaryContainer = PeacockNavy,
    tertiary = IndiaGreen,
    onTertiary = Color.White,
    tertiaryContainer = IndiaGreenLight,
    onTertiaryContainer = Color(0xFF064E3B),
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = BorderLight,
    error = ErrorRed,
    errorContainer = ErrorRedLight,
    onError = Color.White
)

@Composable
fun SkillPlayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep consistent Indian branding by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Retain alias for compatibility if needed
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) = SkillPlayTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
