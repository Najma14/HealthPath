package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.screens.HomeScreen
import org.example.project.screens.LoginScreen
import org.example.project.screens.RegisterScreen
import org.example.project.screens.SearchScreen
import org.example.project.screens.Screen
import org.example.project.screens.SplashScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var screen by remember { mutableStateOf<Screen>(Screen.Splash) }

            when (screen) {
                Screen.Splash -> SplashScreen(
                    onDone = { screen = Screen.Login }
                )
                Screen.Login -> LoginScreen(
                    onLogin = { screen = Screen.Home },
                    onGoToRegister = { screen = Screen.Register }
                )
                Screen.Register -> RegisterScreen(
                    onRegister = { screen = Screen.Home },
                    onBackToLogin = { screen = Screen.Login }
                )
                Screen.Home -> HomeScreen(
                    onLogout = { screen = Screen.Login },
                    onSearch = { screen = Screen.Search }
                )
                Screen.Search -> SearchScreen(
                    onBack = { screen = Screen.Home }
                )
            }
        }
    }
}