package com.nicolascristaldo.foodrecipes.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe

@Composable
fun DetailsScreenHandLer(
    uiState: DetailsScreenUiState,
    isFavorite: (String) -> Boolean,
    onAddFavorite: (Recipe) -> Unit,
    onRemoveFavorite: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is DetailsScreenUiState.Success -> {
            DetailsScreen(
                recipe = uiState.recipe,
                isFavorite = isFavorite,
                onAddFavorite = onAddFavorite,
                onRemoveFavorite = onRemoveFavorite
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
    isFavorite: (String) -> Boolean,
    onAddFavorite: (Recipe) -> Unit,
    onRemoveFavorite: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (recipe != null) {
        var isFavoriteRecipe by remember { mutableStateOf(isFavorite(recipe.id)) }
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
            )
            Text(text = recipe.name)
            Text(text = recipe.instructions)
            FloatingActionButton(
                onClick = {
                    if (isFavoriteRecipe) {
                        onRemoveFavorite(recipe)
                        isFavoriteRecipe = false
                    }
                    else {
                        onAddFavorite(recipe)
                        isFavoriteRecipe = true
                    }
                }
            ) {
                Icon(
                    imageVector = if (isFavoriteRecipe) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            }
        }
    } else {
        Text(
            text = "Recipe not found",
            modifier = modifier
        )
    }
}