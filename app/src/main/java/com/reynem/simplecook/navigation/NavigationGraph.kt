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
import com.reynem.simplecook.api.RecipeViewModel
import com.reynem.simplecook.compound.IngredientsViewModel
import com.reynem.simplecook.database.IngredientStorageViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    ingredientsViewModel: IngredientsViewModel,
    recipeViewModel: RecipeViewModel,
    ingredientsStorageViewModel: IngredientStorageViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            HomeApp(
                ingredientsViewModel = ingredientsViewModel,
                recipeViewModel = recipeViewModel,
                storageViewModel = ingredientsStorageViewModel
            )
        }

        composable(Screens.History.route) {
            HistoryApp(storageViewModel = ingredientsStorageViewModel)
        }

        composable(Screens.Settings.route) {
            SettingsApp()
        }

        composable(Screens.Compound.route) {
            CompoundApp(viewModel = ingredientsViewModel, storageViewModel = ingredientsStorageViewModel)
        }
    }
}