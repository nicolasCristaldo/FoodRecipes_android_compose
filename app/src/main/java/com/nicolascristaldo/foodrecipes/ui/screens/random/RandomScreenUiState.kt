package com.nicolascristaldo.foodrecipes.ui.screens.random

import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview

sealed interface RandomScreenUiState {
    data class Success(
        val recipePreview: RecipePreview?
    ) : RandomScreenUiState

    data object Error : RandomScreenUiState

    data object Loading : RandomScreenUiState
}
