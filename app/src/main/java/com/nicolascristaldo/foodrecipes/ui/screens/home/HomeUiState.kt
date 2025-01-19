package com.nicolascristaldo.foodrecipes.ui.screens.home

import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList

sealed interface HomeUiState {
    data class Success(
        val searchUiState: SearchUiState = SearchUiState.Success(recipePreviewList = null),
        val filterAttributes: FilterAttributes
    ): HomeUiState

    data object Error: HomeUiState

    data object Loading: HomeUiState
}

sealed interface SearchUiState {
    data class Success(
        val recipePreviewList: RecipePreviewList? = null
    ): SearchUiState

    data object Error: SearchUiState

    data object Loading: SearchUiState
}
