package com.nicolascristaldo.foodrecipes.ui.screens.random

import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview

sealed interface RandomScreenUiState {
    data class Success(
        val recipePreview: RecipePreview?
    ): RandomScreenUiState

    data class Error(
        val internetError: Boolean = false,
        val httpError: Boolean = false
    ): RandomScreenUiState

    data object Loading: RandomScreenUiState
}
