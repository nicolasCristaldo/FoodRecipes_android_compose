package com.nicolascristaldo.foodrecipes.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.foodrecipes.domain.GetFilterAttributesUseCase
import com.nicolascristaldo.foodrecipes.domain.GetRecipesByCriteriaUseCase
import com.nicolascristaldo.foodrecipes.domain.GetRecipesByNameUseCase
import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesByNameUseCase: GetRecipesByNameUseCase,
    private val getFilterAttributesUseCase: GetFilterAttributesUseCase,
    private val getRecipesByCriteriaUseCase: GetRecipesByCriteriaUseCase
): ViewModel() {
    private var filterAttributes: FilterAttributes? = null

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getFilterAttributes()
    }

    private fun getFilterAttributes() {
        viewModelScope.launch {
            try {
                filterAttributes = getFilterAttributesUseCase()
                homeUiState = HomeUiState.Success(
                    recipePreviewList = null,
                    filterAttributes = filterAttributes
                )
            } catch (e: IOException) {
                handleError(e)
            }
        }
    }

    fun getRecipesByName(name: String) {
        viewModelScope.launch {
            updateRecipes {
                getRecipesByNameUseCase(name = name)
            }
        }
    }

    fun getRecipesByCriteria(area: String?, category: String?) {
        viewModelScope.launch {
            updateRecipes {
                getRecipesByCriteriaUseCase(
                    area = area,
                    category = category
                )
            }
        }
    }

    private suspend fun updateRecipes(
        getRecipes: suspend () -> RecipePreviewList
    ) {
        homeUiState = HomeUiState.Loading
        try {
            homeUiState = HomeUiState.Success(
                recipePreviewList = getRecipes(),
                filterAttributes = filterAttributes
            )
        } catch (e: IOException) {
            handleError(e)
        }
    }

    private fun handleError(e: Exception) {
        homeUiState = when (e) {
            is IOException -> HomeUiState.Error(internetError = true)
            is HttpException -> HomeUiState.Error(httpError = true)
            else -> HomeUiState.Error()
        }
    }
}