package com.nicolascristaldo.foodrecipes.ui.screens.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview
import com.nicolascristaldo.foodrecipes.ui.components.RecipeCard

@Composable
fun RecipeListScreen(
    recipeList: List<RecipePreview>?,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(recipeList ?: emptyList()) { recipe ->
            RecipeCard(
                recipePreview = recipe,
                onClick = onRecipeClick
            )
        }
    }
}