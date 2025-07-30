package com.reynem.simplecook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp


@Composable
fun CompoundApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()){
        IngredientButton("Tomato")
    }
}

@Composable
fun IngredientButton(ingredient: String){
    var flag by remember { mutableStateOf(false) }
    Button(
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
            text = ingredient
        )
    }
}