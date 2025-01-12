package com.nicolascristaldo.foodrecipes.ui.screens.home

import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList

sealed interface HomeUiState {
    data class Success(
        val searchUiState: SearchUiState = SearchUiState.Success(recipePreviewList = null),
        val filterAttributes: FilterAttributes
    ): HomeUiState

    data class Error(
        val internetError: Boolean = false,
        val httpError: Boolean = false
    ): HomeUiState

    data object Loading: HomeUiState
}

sealed interface SearchUiState {
    data class Success(
        val recipePreviewList: RecipePreviewList? = null
    ): SearchUiState

    data class Error(
        val internetError: Boolean = false,
        val httpError: Boolean = false
    ): SearchUiState

    data object Loading: SearchUiState
}
