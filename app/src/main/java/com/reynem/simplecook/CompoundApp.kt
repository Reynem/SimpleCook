package com.reynem.simplecook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.reynem.simplecook.compound.IngredientsViewModel
import com.reynem.simplecook.database.IngredientStorageViewModel


@Composable
fun CompoundApp(viewModel: IngredientsViewModel, storageViewModel: IngredientStorageViewModel) {
    FlowRow (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ){
        val ingredients by storageViewModel.getAll().observeAsState(emptyList())

        ingredients
            .map { it.copy(name = it.name.uppercase()) }
            .forEach { ingredient ->
                IngredientButton(ingredient = ingredient.name, viewModel = viewModel)
            }
    }
}

@Composable
fun IngredientButton(ingredient: String, viewModel: IngredientsViewModel){
    val buttonOn = viewModel.triggeredIngredients.contains(ingredient)
    Button(
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.inversePrimary),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(containerColor =
            if (buttonOn) {MaterialTheme.colorScheme.primary}
            else {MaterialTheme.colorScheme.surface}
        ),
        onClick = {
            viewModel.toggleIngredient(ingredient)
        }
    )
    {
        Text(
            color = if (buttonOn) {MaterialTheme.colorScheme.onPrimary}
                    else {MaterialTheme.colorScheme.onSurface},
            fontSize = 12f.sp,
            text = ingredient
        )
    }
}