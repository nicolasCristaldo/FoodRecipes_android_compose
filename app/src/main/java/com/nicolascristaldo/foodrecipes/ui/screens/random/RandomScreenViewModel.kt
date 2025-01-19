package com.nicolascristaldo.foodrecipes.ui.screens.random

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.foodrecipes.domain.GetRandomRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomScreenViewModel @Inject constructor(
    private val getRandomRecipeUseCase: GetRandomRecipeUseCase
) : ViewModel() {
    var randomScreenUiState: RandomScreenUiState by mutableStateOf(RandomScreenUiState.Loading)
        private set

    init {
        getRandomRecipe()
    }

    fun getRandomRecipe() {
        viewModelScope.launch {
            randomScreenUiState = RandomScreenUiState.Loading
            randomScreenUiState = try {
                RandomScreenUiState.Success(getRandomRecipeUseCase())
            } catch (e: Exception) {
                RandomScreenUiState.Error
            }
        }
    }
}