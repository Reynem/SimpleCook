package com.reynem.simplecook.Navigation

sealed class Screens(val route: String) {
    data object Home : Screens("Home")
    data object Profile : Screens("Profile")
    data object Settings : Screens("Settings")
}