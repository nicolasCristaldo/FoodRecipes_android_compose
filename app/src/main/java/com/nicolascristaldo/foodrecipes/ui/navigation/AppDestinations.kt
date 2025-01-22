package com.nicolascristaldo.foodrecipes.ui.navigation

import com.nicolascristaldo.foodrecipes.R

sealed class AppDestinations(
    val route: String,
    val titleRes: Int
) {
    data object Home : AppDestinations(
        route = "home",
        titleRes = R.string.app_name
    )

    data object Random : AppDestinations(
        route = "random",
        titleRes = R.string.random_screen_name
    )

    data object Favorites : AppDestinations(
        route = "favorites",
        titleRes = R.string.favorites_screen_name
    )

    data object Details : AppDestinations(
        route = "details/{id}",
        titleRes = R.string.details_screen_name
    ) {
        fun createRoute(id: String) = "details/$id"
    }
}