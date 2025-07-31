package com.reynem.simplecook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.reynem.simplecook.navigation.BottomNavigationBar
import com.reynem.simplecook.navigation.NavigationGraph
import com.reynem.simplecook.ui.theme.SimpleCookTheme
import com.reynem.simplecook.api.ApiClient
import com.reynem.simplecook.api.RecipeViewModel
import com.reynem.simplecook.compound.IngredientsViewModel
import com.reynem.simplecook.database.IngredientStorageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiClient.initialize(applicationContext)
        enableEdgeToEdge()

//        val storageViewModel = IngredientStorageViewModel(application)
//        storageViewModel.initialization()

        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp(){
    val navController = rememberNavController()

    // ViewModels ARE HERE
    val ingredientsViewModel: IngredientsViewModel = viewModel()
    val recipeViewModel: RecipeViewModel = viewModel()
    val ingredientsStorageViewModel: IngredientStorageViewModel = viewModel()

    SimpleCookTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { innerPadding ->
            NavigationGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                ingredientsViewModel = ingredientsViewModel,
                recipeViewModel = recipeViewModel,
                ingredientsStorageViewModel = ingredientsStorageViewModel
            )
        }
    }
}

@Preview
@Composable
fun MainAppPreview(){
    MainApp()
}