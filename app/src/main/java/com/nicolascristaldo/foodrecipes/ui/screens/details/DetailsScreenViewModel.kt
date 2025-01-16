package com.nicolascristaldo.foodrecipes.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.GetRecipeByIdUseCase
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val repository: FoodRecipeRepository
): ViewModel() {
    var detailsScreenUiState: DetailsScreenUiState by mutableStateOf(DetailsScreenUiState.Loading)
    private set

    fun getRecipeById(id: String) {
        viewModelScope.launch {
            detailsScreenUiState = DetailsScreenUiState.Loading
            detailsScreenUiState = try {
                DetailsScreenUiState.Success(getRecipeByIdUseCase(id))
            }
            catch(e: Exception) {
                DetailsScreenUiState.Error(
                    internetError = e is IOException,
                    httpError = e is HttpException
                )
            }
        }
    }

    fun addRecipeToFavorites(recipe: Recipe) {
        viewModelScope.launch {
            repository.insertRecipe(recipe = recipe)
        }
    }

    fun deleteRecipeFromFavorites(recipe: Recipe) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe = recipe)
        }
    }
}