package com.reynem.simplecook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val icon: ImageVector, val title: String) {
    data object History : Screens("History", com.reynem.simplecook.icons.History, "History")
    data object Compound : Screens("Compound", com.reynem.simplecook.icons.Ingredients, "Compound")
    data object Home : Screens("Home", Icons.Default.Home, "Home")
    data object Settings : Screens("Settings", Icons.Default.Settings, "Settings")
}