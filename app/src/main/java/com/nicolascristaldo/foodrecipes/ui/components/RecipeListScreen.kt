package com.nicolascristaldo.foodrecipes.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview

@Composable
fun RecipeListScreen(
    recipeList: List<RecipePreview>?,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(recipeList ?: emptyList()) { recipe ->
            RecipeCard(
                recipePreview = recipe,
                onClick = onRecipeClick,
                modifier = Modifier
                    .padding(8.dp)
                    .aspectRatio(0.7f)
            )
        }
    }
}