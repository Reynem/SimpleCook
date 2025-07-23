package com.reynem.simplecook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.reynem.simplecook.api.RecipeViewModel
import com.reynem.simplecook.ui.theme.SimpleCookTheme

@Composable
fun HomeApp(modifier: Modifier = Modifier, recipeViewModel: RecipeViewModel = viewModel()) {
    var ingredients by remember { mutableStateOf("")}
    val recipes by recipeViewModel.recipes.observeAsState(emptyList())
    val error by recipeViewModel.error.observeAsState("")

    Column(modifier = modifier){
        OutlinedTextField(
            value = ingredients,
            onValueChange = { ingredients = it },
            label = { Text("Ingredients") },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
            recipeViewModel.fetchRecipes(ingredients)
        }) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (error.isNotEmpty()) {
            Text(text = error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        recipes.forEach { recipe ->
            Text(text = recipe.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SimpleCookTheme {
        HomeApp()
    }
}