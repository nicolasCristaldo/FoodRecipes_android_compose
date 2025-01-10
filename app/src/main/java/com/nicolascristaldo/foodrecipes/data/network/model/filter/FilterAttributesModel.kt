package com.nicolascristaldo.foodrecipes.data.network.model.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterAttributesModel(
    @SerialName("strCategory")
    val category: String? = null,
    @SerialName("strArea")
    val area: String? = null
)