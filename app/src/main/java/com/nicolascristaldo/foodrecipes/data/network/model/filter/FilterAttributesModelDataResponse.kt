package com.nicolascristaldo.foodrecipes.data.network.model.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterAttributesModelDataResponse(
    @SerialName("meals")
    val criteria: List<FilterAttributesModel>
)