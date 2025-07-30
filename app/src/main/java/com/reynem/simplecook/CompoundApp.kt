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

val ingredients : List<String> = listOf("Tomato", "Garlic", "Potato")

@Composable
fun CompoundApp(modifier: Modifier = Modifier) {
    FlowRow (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ){
        repeat(ingredients.size){ ingredient ->
            IngredientButton(ingredients[ingredient])
        }
    }
}

@Composable
fun IngredientButton(ingredient: String){
    var flag by remember { mutableStateOf(false) }
    Button(
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.inversePrimary),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(containerColor =
            if (flag) {MaterialTheme.colorScheme.surface}
            else {MaterialTheme.colorScheme.primary}
        ),
        onClick = {
            flag = !flag
        }
    )
    {
        Text(
            color = if (flag) {MaterialTheme.colorScheme.onSurface}
                    else {MaterialTheme.colorScheme.onPrimary},
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