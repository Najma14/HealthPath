package org.example.project.screens

import org.example.project.data.HospitalBrowseMode

sealed interface Screen {
    data object Splash : Screen
    data object Login : Screen
    data object Register : Screen
    data object Home : Screen
    data object Search : Screen
    data class HospitalBrowse(
        val title: String,
        val mode: HospitalBrowseMode,
    ) : Screen
}

