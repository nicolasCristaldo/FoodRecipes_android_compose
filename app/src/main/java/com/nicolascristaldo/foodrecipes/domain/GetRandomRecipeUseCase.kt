package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.model.preview.toPreview
import javax.inject.Inject

class GetRandomRecipeUseCase @Inject constructor(
    private val repository: FoodRecipeRepository
) {
    suspend operator fun invoke() = repository.getRandomRecipe().toPreview().recipes?.get(0)
}