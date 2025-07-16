package com.reynem.simplecook

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.reynem.simplecook.ui.theme.SimpleCookTheme


@Composable
fun HistoryApp(modifier: Modifier = Modifier){
    Column(modifier = modifier){
        UserInformation()
    }
}


@Composable
fun UserInformation() {
    val name = "John Doe"
    val bio = "Android Developer"
    val location = "New York"

    Text(text = name)
    Text(text = bio)
    Text(text = location)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCookTheme {
        HistoryApp()
    }
}