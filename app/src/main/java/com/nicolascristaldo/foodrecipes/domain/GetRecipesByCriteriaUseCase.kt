package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList
import com.nicolascristaldo.foodrecipes.domain.model.preview.toPreview
import javax.inject.Inject

class GetRecipesByCriteriaUseCase @Inject constructor(
    private val repository: FoodRecipeRepository
) {
    suspend operator fun invoke(
        name: String?,
        area: String?,
        category: String?
    ): RecipePreviewList {
        return when {
            name != null -> repository.getRecipesByName(name = name).toPreview()
            area != null -> repository.getRecipesByArea(area = area)
            category != null -> repository.getRecipesByCategory(category = category)
            else -> RecipePreviewList(emptyList())
        }
    }
}