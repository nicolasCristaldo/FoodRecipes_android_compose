package com.nicolascristaldo.foodrecipes.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe

@Composable
fun DetailsScreenHandLer(
    uiState: DetailsScreenUiState,
    modifier: Modifier = Modifier,
) {
    when(uiState) {
        is DetailsScreenUiState.Success -> {
            DetailsScreen(
                recipe = uiState.recipe
            )
        }
        DetailsScreenUiState.Loading -> {
            CircularProgressIndicator()
        }
        is DetailsScreenUiState.Error -> {
            Text(
                text = "Error"
            )
        }
    }
}

@Composable
fun DetailsScreen(
    recipe: Recipe?,
    modifier: Modifier = Modifier,
) {
    if(recipe != null) {
        Column(
            modifier = modifier
        ){
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
            )
            Text(text = recipe.name)
            Text(text = recipe.instructions)
        }
    }
    else {
        Text(
            text = "Recipe not found",
            modifier = modifier
        )
    }
}