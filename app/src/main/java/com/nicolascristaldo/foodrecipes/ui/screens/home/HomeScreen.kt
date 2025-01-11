package com.nicolascristaldo.foodrecipes.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeStateHandler(
    viewModel: HomeViewModel,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    when(viewModel.homeUiState) {
        is HomeUiState.Error -> {
            Text(text = "Error")
        }
        HomeUiState.Loading -> { CircularProgressIndicator() }
        is HomeUiState.Success -> {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

}