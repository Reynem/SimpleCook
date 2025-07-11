package com.reynem.simplecook

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.reynem.simplecook.ui.theme.SimpleCookTheme

@Composable
fun HomeApp(modifier: Modifier = Modifier){
    Column(modifier = modifier){
        Text(text = "Home")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SimpleCookTheme {
        HomeApp()
    }
}