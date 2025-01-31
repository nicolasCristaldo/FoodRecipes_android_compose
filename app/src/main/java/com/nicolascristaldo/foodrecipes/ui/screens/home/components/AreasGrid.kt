package com.nicolascristaldo.foodrecipes.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.nicolascristaldo.foodrecipes.R
import com.nicolascristaldo.foodrecipes.data.providers.FlagsProvider

@Composable
fun AreasGrid(
    areas: List<String>,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(areas) { area ->
            AreaCard(
                name = area,
                filterRecipes = filterRecipes,
                imageRes = FlagsProvider.getFlagByArea(area),
                modifier = Modifier.width(dimensionResource(id = R.dimen.area_card_width))
            )
        }
    }
}

@Composable
fun AreaCard(
    name: String,
    imageRes: Int,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { filterRecipes(null, name, null) },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = name,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}