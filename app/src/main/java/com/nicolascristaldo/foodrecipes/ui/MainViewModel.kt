package com.nicolascristaldo.foodrecipes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.foodrecipes.data.FoodRecipeRepository
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview
import com.nicolascristaldo.foodrecipes.domain.model.preview.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: FoodRecipeRepository
) : ViewModel() {
    private val _favoriteRecipes: StateFlow<List<RecipePreview>> =
        repository.getRecipes()
            .map { entityList -> entityList.map { entity -> entity.toDomain() } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val favoriteRecipes: StateFlow<List<RecipePreview>> get() = _favoriteRecipes

    fun isFavorite(id: String): Boolean = _favoriteRecipes.value.any { it.id == id }
}