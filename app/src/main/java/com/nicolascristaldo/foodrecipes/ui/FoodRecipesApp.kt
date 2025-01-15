package com.nicolascristaldo.foodrecipes.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.foodrecipes.data.providers.NavigationItemsProvider
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
    onSearch: (String) -> Unit,
    currentScreen: AppDestinations,
    modifier: Modifier = Modifier
) {
    var isSearchActive by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            if(isSearchActive && currentScreen is AppDestinations.Home) {
                SearchTextField(
                    onSearch = {
                        onSearch(it)
                        isSearchActive = false
                    }
                )
            }
            else {
                Text(text = stringResource(id = currentScreen.titleRes))
            }
        },
        navigationIcon = {
            if (currentScreen is AppDestinations.Details || isSearchActive) {
                IconButton(
                    onClick = {
                        if(isSearchActive) {
                            isSearchActive = false
                        }
                        else {
                            navigateUp()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        },
        actions = {
            if (currentScreen is AppDestinations.Home && !isSearchActive) {
                IconButton(onClick = { isSearchActive = true}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search"
                    )
                }
            }
        }
    )
}

@Composable
fun SearchTextField(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        value = query,
        onValueChange = { query = it },
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(text = "Search") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
        modifier = Modifier.focusRequester(focusRequester)
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
                        imageVector = if (index == currentItemIndex) navigationItem.selectedIcon
                        else navigationItem.unselectedIcon,
                        contentDescription = navigationItem.name
                    )
                },
                label = {
                    Text(
                        text = navigationItem.name,
                        fontWeight = if (index == currentItemIndex) FontWeight.Bold
                        else FontWeight.Normal
                    )
                }
            )
        }
    }
}