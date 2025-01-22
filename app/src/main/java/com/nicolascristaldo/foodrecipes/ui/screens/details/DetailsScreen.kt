package com.nicolascristaldo.foodrecipes.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nicolascristaldo.foodrecipes.R
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe
import com.nicolascristaldo.foodrecipes.ui.components.ErrorMessageScreen
import com.nicolascristaldo.foodrecipes.ui.components.LoadingScreen
import com.nicolascristaldo.foodrecipes.ui.components.MessageScreen
import com.nicolascristaldo.foodrecipes.ui.screens.details.components.DetailSection
import com.nicolascristaldo.foodrecipes.ui.screens.details.components.DetailsFab
import com.nicolascristaldo.foodrecipes.ui.screens.details.components.DetailsTitleSection
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
                MessageScreen(
                    message = stringResource(id = R.string.no_recipe_found_text),
                    icon = R.drawable.ic_not_found
                )
            }
        }

        DetailsScreenUiState.Loading -> {
            LoadingScreen()
        }

        DetailsScreenUiState.Error -> {
            ErrorMessageScreen(
                message = stringResource(id = R.string.error_loading_recipe_text),
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
    val onClick: (Recipe) -> Unit = if (isFavoriteRecipe) onRemoveFavorite else onAddFavorite

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
            DetailsTitleSection(
                title = recipe.name,
                category = recipe.category,
                modifier = Modifier.padding(top = 12.dp)
            )
            DetailSection(
                title = stringResource(id = R.string.instructions_section_title),
                content = {
                    Text(
                        text = recipe.instructions,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 18.dp)
                    .fillMaxWidth()
            )
            DetailSection(
                title = stringResource(id = R.string.ingredients_section_title),
                content = {
                    IngredientsColumn(
                        ingredients = recipe.ingredients,
                        modifier = Modifier.padding(20.dp)
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
            )
        }
        DetailsFab(
            isFavoriteRecipe = isFavoriteRecipe,
            onClick = {
                onClick(recipe)
                isFavoriteRecipe = !isFavoriteRecipe
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        )
    }
}

