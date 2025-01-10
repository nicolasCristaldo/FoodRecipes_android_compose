package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.model.preview.toPreview
import javax.inject.Inject

class GetRecipesByNameUseCase @Inject constructor(
    private val repository: FoodRecipeRepository
) {
    suspend operator fun invoke(name: String) = repository.getRecipesByName(name = name).toPreview()
}