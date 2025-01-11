package com.nicolascristaldo.foodrecipes.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nicolascristaldo.foodrecipes.ui.navigation.FoodRecipesNavHost

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

