package com.reynem.simplecook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.reynem.simplecook.CompoundApp
import com.reynem.simplecook.HistoryApp
import com.reynem.simplecook.HomeApp
import com.reynem.simplecook.SettingsApp
import com.reynem.simplecook.api.RecipeViewModel
import com.reynem.simplecook.compound.IngredientsViewModel

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier){

    val ingredientsViewModel: IngredientsViewModel = viewModel()
    val recipeViewModel: RecipeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            HomeApp(ingredientsViewModel = ingredientsViewModel, recipeViewModel = recipeViewModel)
        }

        composable(Screens.History.route) {
            HistoryApp()
        }

        composable(Screens.Settings.route) {
            SettingsApp()
        }

        composable(Screens.Compound.route) {
            CompoundApp(viewModel = ingredientsViewModel)
        }
    }
}