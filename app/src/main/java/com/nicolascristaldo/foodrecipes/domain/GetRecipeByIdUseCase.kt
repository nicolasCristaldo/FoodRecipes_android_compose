package com.nicolascristaldo.foodrecipes.domain

import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: FoodRecipeRepository
) {
    suspend operator fun invoke(id: String) = repository.getRecipeById(id).recipes?.get(0)
}