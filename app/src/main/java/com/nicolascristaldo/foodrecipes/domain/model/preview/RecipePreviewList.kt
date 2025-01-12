package com.nicolascristaldo.foodrecipes.domain.model.preview

import com.nicolascristaldo.foodrecipes.data.network.model.preview.RecipeModelPreviewDataResponse
import com.nicolascristaldo.foodrecipes.domain.model.recipe.RecipeList

data class RecipePreviewList(
    val recipes: List<RecipePreview>?
)

fun RecipeModelPreviewDataResponse.toDomain(): RecipePreviewList = RecipePreviewList(
    recipes.map { recipe ->
        recipe.toDomain()
    }
)

fun RecipeList.toPreview(): RecipePreviewList = RecipePreviewList(
    recipes?.map { recipe ->
        recipe.toPreview()
    }
)