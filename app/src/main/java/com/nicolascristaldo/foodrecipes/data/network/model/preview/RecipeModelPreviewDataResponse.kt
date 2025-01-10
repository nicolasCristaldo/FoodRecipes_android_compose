package com.nicolascristaldo.foodrecipes.data.network.model.preview

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeModelPreviewDataResponse(
    @SerialName("meals")
    val recipes: List<RecipeModelPreview>
)