package com.nicolascristaldo.foodrecipes.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.foodrecipes.data.providers.NavigationItemsProvider
import com.nicolascristaldo.foodrecipes.ui.navigation.FoodRecipesNavHost
import com.nicolascristaldo.foodrecipes.ui.screens.details.DetailsScreenViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel
import com.nicolascristaldo.foodrecipes.ui.screens.random.RandomScreenViewModel

@Composable
fun FoodRecipesApp(
    homeViewModel: HomeViewModel = hiltViewModel(),
    randomScreenViewModel: RandomScreenViewModel = hiltViewModel(),
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            FoodRecipesTopAppBar(
                navigateUp = navController::navigateUp
            )
        },
        bottomBar = {
            FoodRecipesBottomNavigationBar(
                navController = navController
            )
        }
    ) { contentPadding ->
        Surface {
            FoodRecipesNavHost(
                homeViewModel = homeViewModel,
                randomScreenViewModel = randomScreenViewModel,
                detailsScreenViewModel = detailsScreenViewModel,
                navController = navController,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRecipesTopAppBar(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Food Recipes")
        }
    )
}

@Composable
fun FoodRecipesBottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItemList = remember { NavigationItemsProvider.getNavigationItems() }
    var currentItemIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        navigationItemList.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = index == currentItemIndex,
                onClick = {
                    currentItemIndex = index
                    navController.navigate(navigationItem.name)
                },
                icon = { 
                    Icon(
                        imageVector = if(index == currentItemIndex) navigationItem.selectedIcon
                        else navigationItem.unselectedIcon,
                        contentDescription = navigationItem.name
                    )
                },
                label = {
                    Text(
                        text = navigationItem.name,
                        fontWeight = if(index == currentItemIndex) FontWeight.Bold
                        else FontWeight.Normal
                    )
                }
            )
        }
    }
}