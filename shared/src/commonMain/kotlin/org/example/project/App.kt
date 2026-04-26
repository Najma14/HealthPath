package org.example.project

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.auth.createAuthStorage
import org.example.project.screens.HomeScreen
import org.example.project.screens.HospitalBrowseScreen
import org.example.project.screens.LoginScreen
import org.example.project.screens.RegisterScreen
import org.example.project.screens.SearchScreen
import org.example.project.screens.Screen
import org.example.project.screens.SplashScreen
import org.example.project.theme.AppTheme
import org.example.project.theme.HealthPathTheme
import org.example.project.theme.LocalAppTheme
import org.example.project.theme.LocalAppThemeSetter

@Composable
@Preview
fun App() {
    var appTheme by remember { mutableStateOf(AppTheme.SYSTEM) }
    val systemDark = isSystemInDarkTheme()
    val useDarkTheme = when (appTheme) {
        AppTheme.SYSTEM -> systemDark
        AppTheme.DARK -> true
        AppTheme.LIGHT -> false
    }

    val themeSetter = remember {
        { selected: AppTheme -> appTheme = selected }
    }

    CompositionLocalProvider(
        LocalAppTheme provides appTheme,
        LocalAppThemeSetter provides themeSetter,
    ) {
        HealthPathTheme(darkTheme = useDarkTheme) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                var screen by remember { mutableStateOf<Screen>(Screen.Splash) }
                val authStorage = remember { createAuthStorage() }
                var favoriteHospitalIds by remember { mutableStateOf(setOf<String>()) }
                val toggleFavorite: (String) -> Unit = { id: String ->
                    favoriteHospitalIds =
                        if (id in favoriteHospitalIds) favoriteHospitalIds - id
                        else favoriteHospitalIds + id
                }

                when (screen) {
                    Screen.Splash -> SplashScreen(
                        onDone = {
                            screen = if (authStorage.hasSession()) Screen.Home else Screen.Login
                        },
                    )
                    Screen.Login -> LoginScreen(
                        onLogin = { email, password ->
                            authStorage.saveSession(email, password)
                            screen = Screen.Home
                        },
                        onGoToRegister = { screen = Screen.Register }
                    )
                    Screen.Register -> RegisterScreen(
                        onRegister = { email, password ->
                            authStorage.saveSession(email, password)
                            screen = Screen.Home
                        },
                        onBackToLogin = { screen = Screen.Login }
                    )
                    Screen.Home -> HomeScreen(
                        onLogout = {
                            authStorage.clear()
                            screen = Screen.Login
                        },
                        onSearch = { screen = Screen.Search },
                        favoriteHospitalIds = favoriteHospitalIds,
                        onToggleFavorite = toggleFavorite,
                        onBrowseHospitals = { title, mode ->
                            screen = Screen.HospitalBrowse(title, mode)
                        },
                    )
                    Screen.Search -> SearchScreen(
                        onBack = { screen = Screen.Home },
                        favoriteHospitalIds = favoriteHospitalIds,
                        onToggleFavorite = toggleFavorite,
                    )
                    is Screen.HospitalBrowse -> {
                        val browse = screen as Screen.HospitalBrowse
                        HospitalBrowseScreen(
                            title = browse.title,
                            mode = browse.mode,
                            favoriteHospitalIds = favoriteHospitalIds,
                            onToggleFavorite = toggleFavorite,
                            onBack = { screen = Screen.Home },
                        )
                    }
                }
            }
        }
    }
}
