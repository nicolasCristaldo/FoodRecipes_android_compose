package com.nicolascristaldo.foodrecipes

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nicolascristaldo.foodrecipes.ui.FoodRecipesApp
import com.nicolascristaldo.foodrecipes.ui.theme.FoodRecipesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodRecipesTheme {
                FoodRecipesApp()
            }
        }
    }
}