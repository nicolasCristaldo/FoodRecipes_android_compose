package com.nicolascristaldo.foodrecipes.domain.model.preview

import com.nicolascristaldo.foodrecipes.data.database.model.RecipePreviewEntity
import com.nicolascristaldo.foodrecipes.data.network.model.preview.RecipeModelPreview
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe

data class RecipePreview(
    val id: String,
    val name: String,
    val imageUrl: String
)

fun RecipeModelPreview.toDomain(): RecipePreview = RecipePreview(
    id = id,
    name = name,
    imageUrl = imageUrl
)

fun Recipe.toPreview(): RecipePreview = RecipePreview(
    id = id,
    name = name,
    imageUrl = imageUrl
)

fun RecipePreviewEntity.toDomain(): RecipePreview = RecipePreview(
    id = id,
    name = name,
    imageUrl = imageUrl
)