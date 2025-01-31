package com.nicolascristaldo.foodrecipes.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.foodrecipes.domain.GetFilterAttributesUseCase
import com.nicolascristaldo.foodrecipes.domain.GetRecipesByCriteriaUseCase
import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreviewList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFilterAttributesUseCase: GetFilterAttributesUseCase,
    private val getRecipesByCriteriaUseCase: GetRecipesByCriteriaUseCase
) : ViewModel() {
    private lateinit var filterAttributes: FilterAttributes

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getFilterAttributes()
    }

    fun getFilterAttributes() {
        viewModelScope.launch {
            try {
                filterAttributes = getFilterAttributesUseCase()
                homeUiState = HomeUiState.Success(
                    filterAttributes = filterAttributes
                )
            } catch (e: Exception) {
                homeUiState = HomeUiState.Error
            }
        }
    }

    fun getRecipesByCriteria(
        name: String? = null,
        area: String? = null,
        category: String? = null
    ) {
        viewModelScope.launch {
            updateRecipes {
                getRecipesByCriteriaUseCase(
                    name = name,
                    area = area,
                    category = category
                )
            }
        }
    }

    private suspend fun updateRecipes(
        getRecipes: suspend () -> RecipePreviewList
    ) {
        val currentState = homeUiState as? HomeUiState.Success ?: return
        homeUiState = currentState.copy(searchUiState = SearchUiState.Loading)

        val searchState = try {
            SearchUiState.Success(recipePreviewList = getRecipes())
        } catch (e: Exception) {
            SearchUiState.Error
        }

        homeUiState = currentState.copy(searchUiState = searchState)
    }
}