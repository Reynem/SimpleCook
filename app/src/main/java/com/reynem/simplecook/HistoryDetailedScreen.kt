package com.reynem.simplecook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil3.compose.SubcomposeAsyncImage
import com.reynem.simplecook.database.models.HistoryRecipe

@Composable
fun HistoryDetailedScreen(recipe: HistoryRecipe, onBack: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
            Button(onClick = onBack) {
                Text("← Back")
            }
            Spacer(Modifier.height(8.dp))
            Text(recipe.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            SubcomposeAsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                loading = { CircularProgressIndicator() }
            )
            Spacer(Modifier.height(8.dp))
            Text("Ready in: ${recipe.readyInMinutes} minutes")
            Text("Servings: ${recipe.servings}")
            Spacer(Modifier.height(16.dp))
            Text(HtmlCompat.fromHtml(recipe.summary, HtmlCompat.FROM_HTML_MODE_COMPACT).toString())
        }
    }
}