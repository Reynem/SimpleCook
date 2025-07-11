package com.reynem.simplecook

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.reynem.simplecook.ui.theme.SimpleCookTheme


@Composable
fun SettingsApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Settings")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SimpleCookTheme {
        SettingsApp()
    }
}