package com.nicolascristaldo.foodrecipes.ui.screens.random

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview
import com.nicolascristaldo.foodrecipes.ui.components.RecipeCard

@Composable
fun RandomStateHandler(
    uiState: RandomScreenUiState,
    onButtonClick: () -> Unit,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when(uiState) {
        is RandomScreenUiState.Success -> {
            RandomScreen(
                recipePreview = uiState.recipePreview,
                onClick = onButtonClick,
                onRecipeClick = onRecipeClick
            )
        }
        RandomScreenUiState.Loading -> {
            CircularProgressIndicator()
        }
        is RandomScreenUiState.Error -> {
            Text(
                text = "Error"
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
        modifier = modifier
    ) {
        if(recipePreview != null) {
            RecipeCard(
                recipePreview = recipePreview,
                onClick = onRecipeClick
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
        Button(onClick = { onClick() }) {
            Text(text = "discover a new recipe!")
        }
    }
}