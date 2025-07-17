package com.reynem.simplecook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.reynem.simplecook.navigation.BottomNavigationBar
import com.reynem.simplecook.navigation.NavigationGraph
import com.reynem.simplecook.ui.theme.SimpleCookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp(){
    val navController = rememberNavController()
    SimpleCookTheme {
        Scaffold(bottomBar = { BottomNavigationBar(navController = navController) })
        { innerPadding ->
            NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Preview
@Composable
fun MainAppPreview(){
    MainApp()
}