package com.nicolascristaldo.foodrecipes.ui.screens.details

import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe

sealed interface DetailsScreenUiState {
    data class Success(
        val recipe: Recipe?
    ): DetailsScreenUiState

    data object Error: DetailsScreenUiState

    data object Loading: DetailsScreenUiState
}