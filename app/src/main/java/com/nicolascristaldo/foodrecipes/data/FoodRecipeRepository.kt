package com.nicolascristaldo.foodrecipes.data

import com.nicolascristaldo.foodrecipes.data.database.RecipeDao
import com.nicolascristaldo.foodrecipes.data.database.model.RecipePreviewEntity
import com.nicolascristaldo.foodrecipes.data.database.model.toDatabase
import com.nicolascristaldo.foodrecipes.data.network.FoodRecipeApiService
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList
import com.nicolascristaldo.foodrecipes.domain.model.preview.toDomain
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe
import com.nicolascristaldo.foodrecipes.domain.model.recipe.RecipeList
import com.nicolascristaldo.foodrecipes.domain.model.recipe.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRecipeRepository @Inject constructor(
    private val service: FoodRecipeApiService,
    private val dao: RecipeDao
) {
    // Api calls

    suspend fun getRecipeById(id: String): RecipeList =
        withContext(Dispatchers.IO) { service.getRecipeById(id = id).toDomain() }

    suspend fun getRandomRecipe(): RecipeList =
        withContext(Dispatchers.IO) { service.getRandomRecipe().toDomain() }

    suspend fun getRecipesByName(name: String): RecipeList =
        withContext(Dispatchers.IO) { service.getRecipesByName(name = name).toDomain() }

    suspend fun getRecipesByCategory(category: String): RecipePreviewList =
        withContext(Dispatchers.IO) { service.getRecipesByCategory(category = category).toDomain() }

    suspend fun getRecipesByArea(area: String): RecipePreviewList =
        withContext(Dispatchers.IO) { service.getRecipesByArea(area = area).toDomain() }

    suspend fun getCategories(): List<String> =
        withContext(Dispatchers.IO) { service.getCategories().criteria.map { it.category.toString() } }

    suspend fun getAreas(): List<String> =
        withContext(Dispatchers.IO) { service.getAreas().criteria.map { it.area.toString() } }

    // Database calls

    fun getRecipes(): Flow<List<RecipePreviewEntity>> = dao.getAllRecipes()

    suspend fun insertRecipe(recipe: Recipe) = dao.insertRecipe(recipe = recipe.toDatabase())

    suspend fun deleteRecipe(recipe: Recipe) = dao.deleteRecipe(recipe = recipe.toDatabase())
}