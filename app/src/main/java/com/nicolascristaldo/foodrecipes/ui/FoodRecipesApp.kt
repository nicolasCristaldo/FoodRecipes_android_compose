package com.nicolascristaldo.foodrecipes.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.foodrecipes.ui.components.FoodRecipesBottomNavigationBar
import com.nicolascristaldo.foodrecipes.ui.components.FoodRecipesTopAppBar
import com.nicolascristaldo.foodrecipes.ui.navigation.AppDestinations
import com.nicolascristaldo.foodrecipes.ui.navigation.FoodRecipesNavHost
import com.nicolascristaldo.foodrecipes.ui.screens.details.DetailsScreenViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomScreenViewModel

@Composable
fun FoodRecipesApp(
    homeViewModel: HomeViewModel = hiltViewModel(),
    randomScreenViewModel: RandomScreenViewModel = hiltViewModel(),
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when (backStackEntry?.destination?.route) {
        AppDestinations.Home.route -> AppDestinations.Home
        AppDestinations.Random.route -> AppDestinations.Random
        AppDestinations.Favorites.route -> AppDestinations.Favorites
        else -> AppDestinations.Details
    }

    Scaffold(
        topBar = {
            FoodRecipesTopAppBar(
                navigateUp = navController::navigateUp,
                onSearch = homeViewModel::getRecipesByCriteria,
                currentScreen = currentScreen
            )
        },
        bottomBar = {
            FoodRecipesBottomNavigationBar(
                navController = navController,
                modifier = Modifier.height(115.dp)
            )
        }
    ) { contentPadding ->
        Surface {
            FoodRecipesNavHost(
                homeViewModel = homeViewModel,
                randomScreenViewModel = randomScreenViewModel,
                detailsScreenViewModel = detailsScreenViewModel,
                mainViewModel = mainViewModel,
                navController = navController,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}




