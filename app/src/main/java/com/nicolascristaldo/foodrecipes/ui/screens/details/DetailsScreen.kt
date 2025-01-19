package com.nicolascristaldo.foodrecipes.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe
import com.nicolascristaldo.foodrecipes.ui.components.ErrorMessageScreen
import com.nicolascristaldo.foodrecipes.ui.components.LoadingScreen
import com.nicolascristaldo.foodrecipes.ui.screens.details.components.IngredientsColumn

@Composable
fun DetailsScreenHandLer(
    uiState: DetailsScreenUiState,
    isFavorite: (String) -> Boolean,
    onAddFavorite: (Recipe) -> Unit,
    onRemoveFavorite: (Recipe) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is DetailsScreenUiState.Success -> {
            if (uiState.recipe != null) {
                DetailsScreen(
                    recipe = uiState.recipe,
                    isFavorite = isFavorite,
                    onAddFavorite = onAddFavorite,
                    onRemoveFavorite = onRemoveFavorite
                )
            } else {
                Text(
                    text = "Recipe not found"
                )
            }
        }
        DetailsScreenUiState.Loading -> { LoadingScreen() }
        DetailsScreenUiState.Error -> {
            ErrorMessageScreen(
                message = "Error loading recipe",
                onClick = { retryAction() }
            )
        }
    }
}

@Composable
fun DetailsScreen(
    recipe: Recipe,
    isFavorite: (String) -> Boolean,
    onAddFavorite: (Recipe) -> Unit,
    onRemoveFavorite: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isFavoriteRecipe by remember { mutableStateOf(isFavorite(recipe.id)) }
    val onClick: (Recipe) -> Unit = if(isFavoriteRecipe) onRemoveFavorite else onAddFavorite

    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
            )
            Text(
                text = recipe.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = "(${recipe.category})",
                textAlign = TextAlign.Center
            )
            Text(
                text = recipe.instructions,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
            IngredientsColumn(
                ingredients = recipe.ingredients,
                modifier = Modifier.padding(32.dp)
            )
        }
        FloatingActionButton(
            onClick = {
                onClick(recipe)
                isFavoriteRecipe = !isFavoriteRecipe
            },
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        ) {
            Icon(
                imageVector = if (isFavoriteRecipe) Icons.Filled.Favorite
                else Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}

