package com.nicolascristaldo.foodrecipes.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.foodrecipes.R
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview

@Composable
fun RecipeListScreen(
    recipeList: List<RecipePreview>?,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.grid_cell_min_size)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(recipeList ?: emptyList()) { recipe ->
            RecipeCard(
                recipePreview = recipe,
                onClick = onRecipeClick,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .aspectRatio(0.7f)
            )
        }
    }
}