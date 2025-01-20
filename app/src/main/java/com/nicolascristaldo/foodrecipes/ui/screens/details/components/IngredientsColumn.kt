package com.nicolascristaldo.foodrecipes.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Ingredient

@Composable
fun IngredientsColumn(
    ingredients: List<Ingredient>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        IngredientItem(
            ingredient = "Ingredient",
            measure = "Measure",
            text = {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        )
        ingredients.forEach { ingredient ->
            IngredientItem(
                ingredient = ingredient.name!!,
                measure = ingredient.measure!!,
                text = {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }
    }
}

@Composable
fun IngredientItem(
    ingredient: String,
    measure: String,
    text: @Composable (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        text(ingredient)
        text(measure)
    }
    HorizontalDivider(
        thickness = 2.dp,
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimaryContainer)
    )
}