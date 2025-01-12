package com.nicolascristaldo.foodrecipes.data.providers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

object NavigationItemsProvider {
    fun getNavigationItems(): List<NavigationItem> {
        return listOf(
            NavigationItem(
                name = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            NavigationItem(
                name = "Random",
                selectedIcon = Icons.Filled.Refresh,
                unselectedIcon = Icons.Outlined.Refresh
            ),
            NavigationItem(
                name = "Favorites",
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.FavoriteBorder
            )
        )
    }
}