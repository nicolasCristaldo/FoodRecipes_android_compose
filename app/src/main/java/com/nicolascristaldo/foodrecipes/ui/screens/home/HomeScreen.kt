package com.nicolascristaldo.foodrecipes.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    when(viewModel.filterAttributesUiState) {
        is FoodRecipesUiState.Success -> {
            val recipeList = (viewModel.filterAttributesUiState as FoodRecipesUiState.Success).filterAttributes?.areas
            if (recipeList != null) {
                if (recipeList.isEmpty()) {
                    Text(
                        text = "No recipes found",
                        modifier = modifier
                            .padding(contentPadding)
                    )
                } else {
                    Column(
                        modifier = modifier
                            .padding(contentPadding)
                    ) {
                        Text(
                            text = recipeList[0]
                        )
                        Text(
                            text = recipeList[1]
                        )
                        Text(
                            text = recipeList[2]
                        )
                    }
                }
            }
        }
        is FoodRecipesUiState.Error -> {
            if ((viewModel.filterAttributesUiState as FoodRecipesUiState.Error).isHttpError()) {
                Text(
                    text = "HTTP Error",
                    modifier = modifier
                        .padding(contentPadding)
                )
            }
            else {
                Text(
                    text = "Internet Error",
                    modifier = modifier
                        .padding(contentPadding)
                )
            }
        }
        is FoodRecipesUiState.Loading -> {
            CircularProgressIndicator()
        }
    }
}