package com.reynem.simplecook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.reynem.simplecook.compound.IngredientsViewModel
import com.reynem.simplecook.database.IngredientStorageViewModel


@Composable
fun CompoundApp(viewModel: IngredientsViewModel, storageViewModel: IngredientStorageViewModel) {
    val categories by storageViewModel.getAllByCategories().observeAsState(emptyMap())
    LazyColumn (
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
        modifier = Modifier
            .fillMaxSize()
    ){

        items(
            items = categories.entries.toList(),
            key = { it.key }
        ){ (category, ingredients) ->
            CategoryContainer(category = category, ingredients = ingredients, viewModel = viewModel)
        }
    }
}

@Composable
fun CategoryContainer(category: String, ingredients: List<String>, viewModel: IngredientsViewModel){
    Card (
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ){
        Text(textAlign = TextAlign.Center, text = category)

        Spacer(modifier = Modifier.width(10.dp))

        FlowRow{
            ingredients.forEach { ingredient ->
                IngredientButton(ingredient = ingredient, viewModel = viewModel)
            }
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