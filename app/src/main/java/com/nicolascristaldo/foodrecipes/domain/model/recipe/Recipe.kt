package com.nicolascristaldo.foodrecipes.domain.model.recipe

import com.nicolascristaldo.foodrecipes.data.network.model.recipe.RecipeModel

data class Recipe(
    val id: String,
    val name: String,
    val instructions: String,
    val area: String,
    val category: String,
    val imageUrl: String,
    val ingredients: List<Ingredient>
)

fun RecipeModel.toDomain(): Recipe {
    val ingredients = listOf(
        Ingredient(ingredient1, measure1),
        Ingredient(ingredient2, measure2),
        Ingredient(ingredient3, measure3),
        Ingredient(ingredient4, measure4),
        Ingredient(ingredient5, measure5),
        Ingredient(ingredient6, measure6),
        Ingredient(ingredient7, measure7),
        Ingredient(ingredient8, measure8),
        Ingredient(ingredient9, measure9),
        Ingredient(ingredient10, measure10),
        Ingredient(ingredient11, measure11),
        Ingredient(ingredient12, measure12),
        Ingredient(ingredient13, measure13),
        Ingredient(ingredient14, measure14),
        Ingredient(ingredient15, measure15),
        Ingredient(ingredient16, measure16),
        Ingredient(ingredient17, measure17),
        Ingredient(ingredient18, measure18),
        Ingredient(ingredient19, measure19),
        Ingredient(ingredient20, measure20)
    ).filter { ingredient ->
        ingredient.isValidIngredient()
    }

    return Recipe(
        id = id,
        name = name,
        instructions = instructions,
        area = area,
        category = category,
        imageUrl = imageUrl,
        ingredients = ingredients
    )
}