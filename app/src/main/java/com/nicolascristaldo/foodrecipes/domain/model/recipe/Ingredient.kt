package com.nicolascristaldo.foodrecipes.domain.model.recipe

data class Ingredient(
    val name: String?,
    val measure: String?
) {
    fun isValidIngredient(): Boolean = !name.isNullOrEmpty() && !measure.isNullOrEmpty()
}
