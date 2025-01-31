package com.nicolascristaldo.foodrecipes.ui.screens.random

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.foodrecipes.R
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview
import com.nicolascristaldo.foodrecipes.ui.components.ErrorMessageScreen
import com.nicolascristaldo.foodrecipes.ui.components.LoadingScreen
import com.nicolascristaldo.foodrecipes.ui.components.RecipeCard

@Composable
fun RandomStateHandler(
    uiState: RandomScreenUiState,
    onButtonClick: () -> Unit,
    onRecipeClick: (String) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is RandomScreenUiState.Success -> {
            RandomScreen(
                recipePreview = uiState.recipePreview,
                onClick = onButtonClick,
                onRecipeClick = onRecipeClick,
                modifier = modifier
            )
        }

        RandomScreenUiState.Loading -> {
            LoadingScreen()
        }

        RandomScreenUiState.Error -> {
            ErrorMessageScreen(
                message = stringResource(id = R.string.error_loading_recipe_text),
                onClick = { retryAction() },
                modifier = modifier
            )
        }
    }
}

@Composable
fun RandomScreen(
    recipePreview: RecipePreview?,
    onClick: () -> Unit,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (recipePreview != null) {
            RecipeCard(
                recipePreview = recipePreview,
                onClick = onRecipeClick,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.random_card_width))
                    .height(dimensionResource(id = R.dimen.random_card_height))
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large_size)))

        }
        Button(onClick = { onClick() }) {
            Text(
                text = stringResource(id = R.string.discover_recipe_text),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}