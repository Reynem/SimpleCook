package com.reynem.simplecook

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reynem.simplecook.ui.theme.SimpleCookTheme


@Composable
fun ProfileApp(modifier: Modifier = Modifier){
    Column(modifier = modifier){
        ProfileContainer()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCookTheme {
        ProfileApp()
    }
}