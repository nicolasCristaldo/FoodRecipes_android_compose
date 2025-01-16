package com.nicolascristaldo.foodrecipes.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesRow(
    categories: List<String>,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
    ) {
        items(categories) { category ->
            Text(
                text = category,
                modifier = Modifier
                    .width(100.dp)
                    .clickable {
                        filterRecipes(null, null, category)
                    }
            )
        }
    }
}