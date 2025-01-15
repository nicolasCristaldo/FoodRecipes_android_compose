package com.nicolascristaldo.foodrecipes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascristaldo.foodrecipes.ui.screens.details.DetailsScreenHandLer
import com.nicolascristaldo.foodrecipes.ui.screens.details.DetailsScreenViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.favorites.FavoritesScreen
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeStateHandler
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomScreenViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomStateHandler

@Composable
fun FoodRecipesNavHost(
    homeViewModel: HomeViewModel,
    randomScreenViewModel: RandomScreenViewModel,
    detailsScreenViewModel: DetailsScreenViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Home.route,
        modifier = modifier
    ) {
        composable(route = AppDestinations.Home.route) {
            HomeStateHandler(
                uiState = homeViewModel.homeUiState,
                filterRecipes = homeViewModel::getRecipesByCriteria,
                onRecipeClick = { id ->
                    navController.navigate(AppDestinations.Details.createRoute(id))
                }
            )
        }

        composable(route = AppDestinations.Random.route) {
            RandomStateHandler(
                uiState = randomScreenViewModel.randomScreenUiState,
                onButtonClick = randomScreenViewModel::getRandomRecipe,
                onRecipeClick = { id ->
                    navController.navigate(AppDestinations.Details.createRoute(id))
                }
            )
        }

        composable(route = AppDestinations.Favorites.route) {
            FavoritesScreen()
        }

        composable(
            route = AppDestinations.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")

            LaunchedEffect(id) {
                detailsScreenViewModel.getRecipeById(id!!)
            }

            DetailsScreenHandLer(
                uiState = detailsScreenViewModel.detailsScreenUiState
            )
        }
    }
}