package org.example.project.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppTheme = compositionLocalOf { AppTheme.SYSTEM }

val LocalAppThemeSetter = staticCompositionLocalOf<(AppTheme) -> Unit> {
    error("LocalAppThemeSetter not provided — wrap UI in App() / CompositionLocalProvider")
}
