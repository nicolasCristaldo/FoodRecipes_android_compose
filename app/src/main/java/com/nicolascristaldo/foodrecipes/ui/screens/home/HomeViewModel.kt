package com.nicolascristaldo.foodrecipes.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.foodrecipes.domain.GetFilterAttributesUseCase
import com.nicolascristaldo.foodrecipes.domain.GetRandomRecipeUseCase
import com.nicolascristaldo.foodrecipes.domain.GetRecipeByIdUseCase
import com.nicolascristaldo.foodrecipes.domain.GetRecipesByCriteriaUseCase
import com.nicolascristaldo.foodrecipes.domain.GetRecipesByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val getRandomRecipeUseCase: GetRandomRecipeUseCase,
    private val getRecipesByNameUseCase: GetRecipesByNameUseCase,
    private val getRecipesByCriteriaUseCase: GetRecipesByCriteriaUseCase,
    private val getFilterAttributesUseCase: GetFilterAttributesUseCase
): ViewModel() {

    // ID
    var recipeByIdUiState: FoodRecipesUiState by mutableStateOf(FoodRecipesUiState.Loading)
        private set

    var randomRecipeUiState: FoodRecipesUiState by mutableStateOf(FoodRecipesUiState.Loading)
        private set

    var recipeByNameUiState: FoodRecipesUiState by mutableStateOf(FoodRecipesUiState.Loading)
        private set

    var recipeByCriteriaUiState: FoodRecipesUiState by mutableStateOf(FoodRecipesUiState.Loading)
        private set

    var filterAttributesUiState: FoodRecipesUiState by mutableStateOf(FoodRecipesUiState.Loading)
        private set

    init {
        getRecipeById("52893")
        getRandomRecipe()
        getRecipeByName("Apple")
        getRecipeByCriteria(area = null, category = "Seafood")
        getFilterAttributes()
    }

    fun getRecipeById(id: String) {
        viewModelScope.launch {
            recipeByIdUiState = FoodRecipesUiState.Loading
            recipeByIdUiState = try {
                FoodRecipesUiState.Success(
                    recipeList = getRecipeByIdUseCase(id = id)
                )
            }
            catch(e: IOException) {
                FoodRecipesUiState.Error(internetError = true)
            }
            catch (e: HttpException) {
                Log.e("API_ERROR", "HTTP Error: ${e.code()} - ${e.message()}")
                FoodRecipesUiState.Error(httpError = true)
            }
        }
    }

    // RANDOM

    fun getRandomRecipe() {
        viewModelScope.launch {
            randomRecipeUiState = FoodRecipesUiState.Loading
            randomRecipeUiState = try {
                FoodRecipesUiState.Success(
                    recipeList = getRandomRecipeUseCase()
                )
            }
            catch(e: IOException) {
                FoodRecipesUiState.Error(internetError = true)
            }
            catch (e: HttpException) {
                Log.e("API_ERROR", "HTTP Error: ${e.code()} - ${e.message()}")
                FoodRecipesUiState.Error(httpError = true)
            }
        }
    }

    // NAME

    fun getRecipeByName(name: String) {
        viewModelScope.launch {
            recipeByNameUiState = FoodRecipesUiState.Loading
            recipeByNameUiState = try {
                FoodRecipesUiState.Success(
                    recipePreviewList = getRecipesByNameUseCase(name = name)
                )
            }
            catch(e: IOException) {
                FoodRecipesUiState.Error(internetError = true)
            }
            catch (e: HttpException) {
                Log.e("API_ERROR", "HTTP Error: ${e.code()} - ${e.message()}")
                FoodRecipesUiState.Error(httpError = true)
            }
        }
    }

    // criteria

    fun getRecipeByCriteria(area: String?, category: String?) {
        viewModelScope.launch {
            recipeByCriteriaUiState = FoodRecipesUiState.Loading
            recipeByCriteriaUiState = try {
                FoodRecipesUiState.Success(
                    recipePreviewList = getRecipesByCriteriaUseCase(area = area, category = category)
                )
            }
            catch(e: IOException) {
                FoodRecipesUiState.Error(internetError = true)
            }
            catch (e: HttpException) {
                Log.e("API_ERROR", "HTTP Error: ${e.code()} - ${e.message()}")
                FoodRecipesUiState.Error(httpError = true)
            }
        }
    }

    fun getFilterAttributes() {
        viewModelScope.launch {
            filterAttributesUiState = FoodRecipesUiState.Loading
            filterAttributesUiState = try {
                FoodRecipesUiState.Success(
                    filterAttributes = getFilterAttributesUseCase()
                )
            }
            catch(e: IOException) {
                FoodRecipesUiState.Error(internetError = true)
            }
            catch (e: HttpException) {
                Log.e("API_ERROR", "HTTP Error: ${e.code()} - ${e.message()}")
                FoodRecipesUiState.Error(httpError = true)
            }
        }
    }
}