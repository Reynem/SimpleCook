package com.reynem.simplecook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.reynem.simplecook.navigation.NavigationScreen
import com.reynem.simplecook.ui.theme.SimpleCookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp(Modifier.fillMaxSize())
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier){
    SimpleCookTheme{
        Scaffold(modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
            Column{
                ProfileContainer(modifier = Modifier.padding(innerPadding))
                NavigationScreen()
            }
        }
    }
}

@Composable
fun ProfileContainer(modifier: Modifier = Modifier){
    val image = R.drawable.profile_picture
    val imageModifier = modifier
        .size(120.dp)
        .padding(10.dp)
        .clip(CircleShape)


    Image(
        painter = painterResource(id = image),
        contentDescription = "Profile Picture",
        modifier = imageModifier,
    )
}