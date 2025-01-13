package com.nicolascristaldo.foodrecipes.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicolascristaldo.foodrecipes.ui.screens.favorites.FavoritesScreen
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeStateHandler
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomScreenViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomStateHandler

@Composable
fun FoodRecipesNavHost(
    homeViewModel: HomeViewModel,
    randomScreenViewModel: RandomScreenViewModel,
    navController: NavHostController,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = modifier
    ) {
        composable(route = "Home") {
            HomeStateHandler(
                uiState = homeViewModel.homeUiState,
                filterRecipes = homeViewModel::getRecipesByCriteria,
                modifier = Modifier.padding(contentPadding),
            )
        }

        composable(route = "Random") {
            RandomStateHandler(
                uiState = randomScreenViewModel.randomScreenUiState,
                onClick = randomScreenViewModel::getRandomRecipe,
                modifier = Modifier.padding(contentPadding)
            )
        }

        composable(route = "Favorites") {
            FavoritesScreen()
        }
    }
}