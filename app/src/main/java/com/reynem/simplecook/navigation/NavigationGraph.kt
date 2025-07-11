package com.reynem.simplecook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.reynem.simplecook.MainActivity
import com.reynem.simplecook.ProfileActivity
import com.reynem.simplecook.SettingsActivity

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            MainActivity()
        }

        composable(Screens.Profile.route) {
            ProfileActivity()
        }

        composable(Screens.Settings.route) {
            SettingsActivity()
        }
    }
}