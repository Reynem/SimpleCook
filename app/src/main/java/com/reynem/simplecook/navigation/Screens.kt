package com.reynem.simplecook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val icon: ImageVector, val title: String) {

    data object Home : Screens("Home", Icons.Default.Home, "Home")
    data object Profile : Screens("Profile", Icons.Default.AccountCircle, "Profile")
    data object Settings : Screens("Settings", Icons.Default.Settings, "Settings")
}