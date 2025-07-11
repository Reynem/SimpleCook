package com.reynem.simplecook

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.reynem.simplecook.navigation.NavigationScreen
import com.reynem.simplecook.ui.theme.SimpleCookTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCookTheme {
                ProfileApp(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileApp(modifier: Modifier = Modifier){
    SimpleCookTheme{
        Scaffold(modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
            Column{
                NavigationScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCookTheme {
        ProfileApp()
    }
}