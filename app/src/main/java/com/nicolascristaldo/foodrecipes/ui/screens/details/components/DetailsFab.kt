package com.nicolascristaldo.foodrecipes.ui.screens.details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailsFab(
    isFavoriteRecipe: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isFavoriteRecipe) Icons.Filled.Favorite
            else Icons.Outlined.FavoriteBorder,
            contentDescription = null
        )
    }
}