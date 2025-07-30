package com.reynem.simplecook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.reynem.simplecook.compound.IngredientsViewModel

val ingredients : List<String> = listOf("Tomato", "Garlic", "Potato")
val viewModel: IngredientsViewModel = IngredientsViewModel()

@Composable
fun CompoundApp() {
    FlowRow (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ){
        repeat(ingredients.size){
            IngredientButton(ingredient = ingredients[it])
        }
    }
}

@Composable
fun IngredientButton(ingredient: String){
    var buttonOn by remember { mutableStateOf(false) }
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
            buttonOn = !buttonOn
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

@Preview
@Composable
fun IngredientButtonPreview(){
    CompoundApp()
}