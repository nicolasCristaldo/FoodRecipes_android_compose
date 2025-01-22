package com.nicolascristaldo.foodrecipes.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascristaldo.foodrecipes.R
import com.nicolascristaldo.foodrecipes.ui.screens.details.DetailsScreenHandLer
import com.nicolascristaldo.foodrecipes.ui.screens.details.DetailsScreenViewModel
import com.nicolascristaldo.foodrecipes.ui.MainViewModel
import com.nicolascristaldo.foodrecipes.ui.components.MessageScreen
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeStateHandler
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel
import com.nicolascristaldo.foodrecipes.ui.components.RecipeListScreen
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomScreenViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomStateHandler

@Composable
fun FoodRecipesNavHost(
    homeViewModel: HomeViewModel,
    randomScreenViewModel: RandomScreenViewModel,
    detailsScreenViewModel: DetailsScreenViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val favoriteRecipes by mainViewModel.favoriteRecipes.collectAsState()

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
                },
                retryAction = homeViewModel::getFilterAttributes
            )
        }

        composable(route = AppDestinations.Random.route) {
            RandomStateHandler(
                uiState = randomScreenViewModel.randomScreenUiState,
                onButtonClick = randomScreenViewModel::getRandomRecipe,
                onRecipeClick = { id ->
                    navController.navigate(AppDestinations.Details.createRoute(id))
                },
                retryAction = randomScreenViewModel::getRandomRecipe,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = AppDestinations.Favorites.route) {
            if (favoriteRecipes.isEmpty()) {
                MessageScreen(
                    message = stringResource(id = R.string.no_recipes_saved_text),
                    icon = R.drawable.ic_add_favorite
                )
            } else {
                RecipeListScreen(
                    recipeList = favoriteRecipes,
                    onRecipeClick = { id ->
                        navController.navigate(AppDestinations.Details.createRoute(id))
                    }
                )
            }
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
                uiState = detailsScreenViewModel.detailsScreenUiState,
                isFavorite = {
                    mainViewModel.isFavorite(it)
                },
                onRemoveFavorite = {
                    detailsScreenViewModel.deleteRecipeFromFavorites(it)
                },
                onAddFavorite = {
                    detailsScreenViewModel.addRecipeToFavorites(it)
                },
                retryAction = { detailsScreenViewModel.getRecipeById(id!!) }
            )
        }
    }
}