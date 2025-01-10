package com.nicolascristaldo.foodrecipes.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeScreen
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel

@Composable
fun FoodRecipesNavHost(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable(route = "home") {
            HomeScreen(
                viewModel = viewModel,
                contentPadding = contentPadding
            )
        }
    }
}