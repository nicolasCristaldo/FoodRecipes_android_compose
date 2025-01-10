package com.nicolascristaldo.foodrecipes.ui.screens.home

import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList
import com.nicolascristaldo.foodrecipes.domain.model.recipe.RecipeList

sealed interface FoodRecipesUiState {
    data class Success(
        val recipeList: RecipeList? = null,
        val recipePreviewList: RecipePreviewList? = null,
        val filterAttributes: FilterAttributes? = null
    ): FoodRecipesUiState

    data class Error(
        val internetError: Boolean = false,
        val httpError: Boolean = false
    ): FoodRecipesUiState {
        fun isHttpError() = httpError
    }

    data object Loading: FoodRecipesUiState
}
