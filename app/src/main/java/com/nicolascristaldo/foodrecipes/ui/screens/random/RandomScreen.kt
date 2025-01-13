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
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(uiState) {
        is RandomScreenUiState.Success -> {
            RandomScreen(
                recipePreview = uiState.recipePreview,
                onClick = onClick,
                modifier = modifier
            )
        }
        RandomScreenUiState.Loading -> {
            CircularProgressIndicator(modifier = modifier)
        }
        is RandomScreenUiState.Error -> {
            Text(
                text = "Error",
                modifier = modifier
            )
        }
    }
}

@Composable
fun RandomScreen(
    recipePreview: RecipePreview?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if(recipePreview != null) {
            RecipeCard(recipePreview = recipePreview)
            Spacer(modifier = Modifier.height(16.dp))

        }
        Button(onClick = { onClick() }) {
            Text(text = "discover a new recipe!")
        }
    }
}