package com.reynem.simplecook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.SubcomposeAsyncImage
import com.reynem.simplecook.api.RecipeViewModel
import com.reynem.simplecook.api.models.Recipe
import com.reynem.simplecook.ui.theme.SimpleCookTheme

@Composable
fun HomeApp(modifier: Modifier = Modifier, recipeViewModel: RecipeViewModel = viewModel()) {
    var ingredients by remember { mutableStateOf("")}
    val recipes by recipeViewModel.recipes.observeAsState(emptyList())
    val error by recipeViewModel.error.observeAsState("")

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = ingredients,
            onValueChange = { ingredients = it },
            label = { Text("Ingredients") },
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
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(recipes) { recipe ->
                RecipeItem(recipe = recipe)
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Row (
        modifier = Modifier
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .padding(vertical = 16.dp)
    ){
        Box(
            modifier = Modifier
                .height(240.dp)
                .padding(horizontal = 8.dp)
        ) {
            SubcomposeAsyncImage(
                model = recipe.image,
                contentDescription = "Recipe image " + recipe.id,
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier.width(240.dp)
        ){
            Text(text = recipe.title)
            Text(
                text = stringResource(R.string.number_of_ingredients, recipe.usedIngredientCount),
                color = Color.Gray,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
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