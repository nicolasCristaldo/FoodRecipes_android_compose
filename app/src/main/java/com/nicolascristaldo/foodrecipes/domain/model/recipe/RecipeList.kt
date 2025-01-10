package com.nicolascristaldo.foodrecipes.domain.model.recipe

import com.nicolascristaldo.foodrecipes.data.network.model.recipe.RecipeModelDataResponse

data class RecipeList(
    val recipes: List<Recipe>
)

fun RecipeModelDataResponse.toDomain(): RecipeList = RecipeList(
    recipes.map { recipe ->
        recipe.toDomain()
    }
)
