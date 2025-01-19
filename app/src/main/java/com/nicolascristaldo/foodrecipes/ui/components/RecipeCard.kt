package com.nicolascristaldo.foodrecipes.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = recipePreview.imageUrl,
                contentDescription = recipePreview.name,
            )
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = recipePreview.name,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                )
            }
        }
    }
}