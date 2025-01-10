package com.nicolascristaldo.foodrecipes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.foodrecipes.ui.navigation.FoodRecipesNavHost
import com.nicolascristaldo.foodrecipes.ui.screens.home.FoodRecipesUiState
import com.nicolascristaldo.foodrecipes.ui.screens.home.HomeViewModel

@Composable
fun FoodRecipesApp(
    modifier: Modifier = Modifier
) {

    Scaffold(

    ) { contentPadding ->
        Surface{
            FoodRecipesNavHost(
                contentPadding = contentPadding
            )
        }
    }

}

