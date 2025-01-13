package com.nicolascristaldo.foodrecipes.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.domain.model.preview.RecipePreview
import com.nicolascristaldo.foodrecipes.ui.components.RecipeCard

@Composable
fun HomeStateHandler(
    uiState: HomeUiState,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    when(uiState) {
        is HomeUiState.Success -> {
            HomeScreen(
                state = uiState.searchUiState,
                filterAttributes = uiState.filterAttributes,
                filterRecipes = filterRecipes,
                modifier = modifier
            )
        }
        HomeUiState.Loading -> {
            CircularProgressIndicator(modifier)
        }
        is HomeUiState.Error -> {
            Text(
                text = "Error",
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeScreen(
    state: SearchUiState,
    filterAttributes: FilterAttributes,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HomeSection(
            title = "Areas",
            content = {
                AreaAttributeRow(
                    areas = filterAttributes.areas,
                    filterRecipes = filterRecipes
                )
            },
            modifier = Modifier.height(100.dp)
        )
        HorizontalDivider()
        HomeSection(
            title = "Categories",
            content = {
                CategoryAttributeGrid(
                    categories = filterAttributes.categories,
                    filterRecipes = filterRecipes
                )
            },
            modifier = Modifier.height(100.dp)
        )
        HorizontalDivider()
        when(state) {
            is SearchUiState.Success -> {
                RecipeGrid(recipeList = state.recipePreviewList?.recipes)
            }
            is SearchUiState.Error -> {
                Text(text = "Error")
            }
            SearchUiState.Loading -> { CircularProgressIndicator() }
        }
    }
}

@Composable
fun HomeSection(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = title)
        content()
    }
}

@Composable
fun AreaAttributeRow(
    areas: List<String>,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow {
        items(areas) { area ->
            Text(
                text = area,
                modifier = Modifier.clickable {
                    filterRecipes(null, area, null)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun CategoryAttributeGrid(
    categories: List<String>,
    filterRecipes: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
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

@Composable
fun RecipeGrid(
    recipeList: List<RecipePreview>?,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(recipeList ?: emptyList()) { recipe ->
            RecipeCard(recipePreview = recipe)
        }
    }
}
