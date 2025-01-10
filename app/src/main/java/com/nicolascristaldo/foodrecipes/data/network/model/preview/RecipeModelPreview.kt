package com.nicolascristaldo.foodrecipes.data.network.model.preview

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeModelPreview(
    @SerialName("idMeal")
    val id: String,
    @SerialName("strMeal")
    val name: String,
    @SerialName("strMealThumb")
    val imageUrl: String
)