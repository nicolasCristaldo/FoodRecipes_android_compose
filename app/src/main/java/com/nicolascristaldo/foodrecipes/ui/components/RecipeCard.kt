package com.nicolascristaldo.foodrecipes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview

@Composable
fun RecipeCard(
    recipePreview: RecipePreview,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick(recipePreview.id) },
        modifier = modifier
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = recipePreview.imageUrl,
                contentDescription = recipePreview.name,
            )
            Text(text = "name: ${recipePreview.name}")
            Text(text = "id: ${recipePreview.id}")
        }
    }
}