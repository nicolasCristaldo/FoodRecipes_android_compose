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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.nicolascristaldo.foodrecipes.R
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
            ingredient = stringResource(id = R.string.ingredient_item_text),
            measure = stringResource(id = R.string.measure_item_text),
            text = {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.padding_large))
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
                            .padding(dimensionResource(id = R.dimen.padding_small))
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
        thickness = dimensionResource(id = R.dimen.divider_thickness),
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimaryContainer)
    )
}