package com.nicolascristaldo.foodrecipes.data.network

import com.nicolascristaldo.foodrecipes.data.network.model.filter.FilterAttributesModelDataResponse
import com.nicolascristaldo.foodrecipes.data.network.model.preview.RecipeModelPreviewDataResponse
import com.nicolascristaldo.foodrecipes.data.network.model.recipe.RecipeModelDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipeApiService {
    @GET("/api/json/v1/1/lookup.php?")
    suspend fun getRecipeById(@Query("i") id: String): RecipeModelDataResponse

    @GET("/api/json/v1/1/random.php")
    suspend fun getRandomRecipe(): RecipeModelDataResponse

    @GET("/api/json/v1/1/search.php?")
    suspend fun getRecipesByName(@Query("s") name: String): RecipeModelDataResponse

    @GET("/api/json/v1/1/filter.php?")
    suspend fun getRecipesByCategory(@Query("c") category: String): RecipeModelPreviewDataResponse

    @GET("/api/json/v1/1/filter.php?")
    suspend fun getRecipesByArea(@Query("a") area: String): RecipeModelPreviewDataResponse

    @GET("/api/json/v1/1/list.php?a=list")
    suspend fun getAreas(): FilterAttributesModelDataResponse

    @GET("/api/json/v1/1/list.php?c=list")
    suspend fun getCategories(): FilterAttributesModelDataResponse
}