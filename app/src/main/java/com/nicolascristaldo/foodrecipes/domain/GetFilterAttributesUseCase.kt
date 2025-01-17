package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import javax.inject.Inject

class GetFilterAttributesUseCase @Inject constructor(
    private val repository: FoodRecipeRepository
) {
    suspend operator fun invoke() = FilterAttributes(
        categories = repository.getCategories(),
        areas = repository.getAreas().filter { area -> area != "Unknown" }
    )
}