package com.nicolascristaldo.foodrecipes.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.ui.screens.home.components.AreasGrid
import com.nicolascristaldo.foodrecipes.ui.screens.home.components.CategoriesRow
import com.nicolascristaldo.foodrecipes.ui.screens.list.RecipeListScreen

@Composable
fun HomeStateHandler(
    uiState: HomeUiState,
    filterRecipes: (String?, String?, String?) -> Unit,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when(uiState) {
        is HomeUiState.Success -> {
            HomeScreen(
                state = uiState.searchUiState,
                filterAttributes = uiState.filterAttributes,
                filterRecipes = filterRecipes,
                onRecipeClick = onRecipeClick
            )
        }
        HomeUiState.Loading -> {
            CircularProgressIndicator()
        }
        is HomeUiState.Error -> {
            Text(
                text = "Error"
            )
        }
    }
}

@Composable
fun HomeScreen(
    state: SearchUiState,
    filterAttributes: FilterAttributes,
    filterRecipes: (String?, String?, String?) -> Unit,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HomeSection(
            title = "Categories",
            content = {
                CategoriesRow(
                    categories = filterAttributes.categories,
                    filterRecipes = filterRecipes
                )
            }
        )

        HomeSection(
            title = "Areas",
            content = {
                AreasGrid(
                    areas = filterAttributes.areas,
                    filterRecipes = filterRecipes,
                    modifier = Modifier.height(160.dp)
                )
            }
        )

        when(state) {
            is SearchUiState.Success -> {
                RecipeListScreen(
                    recipeList = state.recipePreviewList?.recipes,
                    onRecipeClick = onRecipeClick
                )
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
        Text(
            text = title,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .paddingFromBaseline(top = 30.dp, bottom = 8.dp)
        )
        content()
        HorizontalDivider()
    }
}






