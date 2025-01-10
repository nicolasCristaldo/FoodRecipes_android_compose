package com.nicolascristaldo.foodrecipes.data.network.model.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeModelDataResponse(
    @SerialName("meals")
    val recipes: List<RecipeModel>
)