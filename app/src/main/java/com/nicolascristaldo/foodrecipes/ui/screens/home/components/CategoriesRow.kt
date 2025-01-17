package com.nicolascristaldo.foodrecipes.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.data.providers.CategoryImagesProvider

@Composable
fun CategoriesRow(
    categories: List<String>,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                imageRes = CategoryImagesProvider.getImageByCategory(category = category),
                modifier = Modifier
                    .clickable { filterRecipes(null, null, category) }
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: String,
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
        )
        Text(
            text = category,
            textAlign = TextAlign.Center,
            modifier = Modifier.paddingFromBaseline(
                top = 18.dp,
                bottom = 12.dp
            )
        )
    }
}