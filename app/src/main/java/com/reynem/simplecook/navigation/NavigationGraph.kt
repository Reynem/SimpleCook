package com.reynem.simplecook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.reynem.simplecook.CompoundApp
import com.reynem.simplecook.HistoryApp
import com.reynem.simplecook.HomeApp
import com.reynem.simplecook.SettingsApp

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            HomeApp()
        }

        composable(Screens.History.route) {
            HistoryApp()
        }

        composable(Screens.Settings.route) {
            SettingsApp()
        }

        composable(Screens.Compound.route) {
            CompoundApp()
        }
    }
}