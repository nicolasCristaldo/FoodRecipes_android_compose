package com.nicolascristaldo.foodrecipes.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.foodrecipes.R
import com.nicolascristaldo.foodrecipes.domain.model.filter.FilterAttributes
import com.nicolascristaldo.foodrecipes.ui.components.ErrorMessageScreen
import com.nicolascristaldo.foodrecipes.ui.components.LoadingScreen
import com.nicolascristaldo.foodrecipes.ui.components.MessageScreen
import com.nicolascristaldo.foodrecipes.ui.components.RecipeListScreen
import com.nicolascristaldo.foodrecipes.ui.screens.home.components.AreasGrid
import com.nicolascristaldo.foodrecipes.ui.screens.home.components.CategoriesRow
import com.nicolascristaldo.foodrecipes.ui.screens.home.components.HomeSection

@Composable
fun HomeStateHandler(
    uiState: HomeUiState,
    filterRecipes: (String?, String?, String?) -> Unit,
    onRecipeClick: (String) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is HomeUiState.Success -> {
            HomeScreen(
                state = uiState.searchUiState,
                filterAttributes = uiState.filterAttributes,
                filterRecipes = filterRecipes,
                onRecipeClick = onRecipeClick
            )
        }

        HomeUiState.Loading -> {
            LoadingScreen()
        }

        HomeUiState.Error -> {
            ErrorMessageScreen(
                message = stringResource(id = R.string.error_loading_recipe_text),
                onClick = { retryAction() },
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
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HomeSection(
            title = stringResource(id = R.string.categories_section_title),
            content = {
                CategoriesRow(
                    categories = filterAttributes.categories,
                    filterRecipes = filterRecipes
                )
            }
        )

        HomeSection(
            title = stringResource(id = R.string.areas_section_title),
            content = {
                AreasGrid(
                    areas = filterAttributes.areas,
                    filterRecipes = filterRecipes,
                    modifier = Modifier.height(160.dp)
                )
            }
        )

        when (state) {
            is SearchUiState.Success -> {
                if(state.recipePreviewList?.recipes.isNullOrEmpty()) {
                    MessageScreen(
                        message = stringResource(id = R.string.no_recipes_found_text),
                        icon = R.drawable.ic_not_found
                    )
                }
                else {
                    RecipeListScreen(
                        recipeList = state.recipePreviewList?.recipes,
                        onRecipeClick = onRecipeClick
                    )
                }
            }

            is SearchUiState.Idle -> {
                MessageScreen(
                    message = stringResource(id = R.string.search_any_recipe_text),
                    icon = R.drawable.ic_search
                )
            }

            SearchUiState.Loading -> {
                LoadingScreen()
            }

            SearchUiState.Error -> {
                Text(
                    text = stringResource(id = R.string.error_loading_recipe_text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}








