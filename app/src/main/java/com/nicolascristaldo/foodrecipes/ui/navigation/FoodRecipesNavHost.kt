package com.nicolascristaldo.foodrecipes.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.foodrecipes.ui.screens.favorites.FavoritesScreen
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeStateHandler
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomScreen

@Composable
fun FoodRecipesNavHost(
    homeViewModel: HomeViewModel,
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
                contentPadding = contentPadding,
                filterRecipes = homeViewModel::getRecipesByCriteria
            )
        }

        composable(route = "Random") {
            RandomScreen()
        }

        composable(route = "Favorites") {
            FavoritesScreen()
        }
    }
}