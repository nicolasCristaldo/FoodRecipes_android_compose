package com.nicolascristaldo.foodrecipes.ui.screens.details

import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe

sealed interface DetailsScreenUiState {
    data class Success(
        val recipe: Recipe?
    ): DetailsScreenUiState

    data class Error(
        val internetError: Boolean = false,
        val httpError: Boolean = false
    ): DetailsScreenUiState

    data object Loading: DetailsScreenUiState
}