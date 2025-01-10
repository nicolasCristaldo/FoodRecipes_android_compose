package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList
import javax.inject.Inject

class GetRecipesByCriteriaUseCase @Inject constructor(
    private val repository: FoodRecipeRepository
) {
    suspend operator fun invoke(area: String?, category: String?): RecipePreviewList {
        return when {
            area != null -> repository.getRecipesByArea(area = area)
            category != null -> repository.getRecipesByCategory(category = category)
            else -> RecipePreviewList(emptyList())
        }
    }
}