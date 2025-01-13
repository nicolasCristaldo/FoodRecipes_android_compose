package com.nicolascristaldo.foodrecipes.ui.navigation

sealed class NavDestinations(val route: String) {
    object Home: NavDestinations("Home")

    object Random: NavDestinations("Random")

    object Favorites: NavDestinations("Favorites")

    data object Details: NavDestinations("Details/{id}") {
        fun createRoute(id: String) = "Details/$id"
    }
}