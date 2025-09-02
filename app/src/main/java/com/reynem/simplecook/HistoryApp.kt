package com.reynem.simplecook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.reynem.simplecook.database.IngredientStorageViewModel
import com.reynem.simplecook.database.models.HistoryRecipe


@Composable
fun HistoryApp(storageViewModel: IngredientStorageViewModel){
    val historyList by storageViewModel.getWholeHistory().observeAsState(emptyList())
    var selectedRecipe by remember { mutableStateOf<HistoryRecipe?>(null) }

    if (selectedRecipe != null) {
        HistoryDetailedScreen(recipe = selectedRecipe!!) { selectedRecipe = null }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(48.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                items = historyList,
                key = { it.id }
            ) { recipe ->
                HistoryObject(recipe = recipe, onViewClick = { selectedRecipe = recipe })
            }
        }
    }
}


@Composable
fun HistoryObject(recipe: HistoryRecipe, onViewClick: () -> Unit = {}) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Row (modifier = Modifier.padding(vertical = 12.dp)) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .padding(horizontal = 8.dp)
            ) {
                SubcomposeAsyncImage(
                    model = recipe.image,
                    contentDescription = "Recipe image " + recipe.id,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        CircularProgressIndicator(modifier = Modifier.wrapContentSize())
                    }
                )
            }
            Column (
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ){
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(
                        id = R.string.ready_in_minutes,
                        recipe.readyInMinutes
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = stringResource(
                        id = R.string.servings,
                        recipe.servings
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )

                MicroButton(stringResource(
                    R.string.view_recipe)
                ) { onViewClick() }
            }
        }
    }
}