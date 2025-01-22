package com.nicolascristaldo.foodrecipes.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.nicolascristaldo.foodrecipes.data.providers.NavigationItemsProvider

@Composable
fun FoodRecipesBottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItemList = remember { NavigationItemsProvider.getNavigationItems() }
    var currentItemIndex by remember { mutableIntStateOf(0) }

    NavigationBar(
        modifier = modifier
    ) {
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