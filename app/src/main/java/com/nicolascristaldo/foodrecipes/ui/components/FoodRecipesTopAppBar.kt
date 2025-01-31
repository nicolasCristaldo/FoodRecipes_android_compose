package com.nicolascristaldo.foodrecipes.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.foodrecipes.R
import com.nicolascristaldo.foodrecipes.ui.navigation.AppDestinations

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
            if (isSearchActive && currentScreen is AppDestinations.Home) {
                SearchTextField(
                    onSearch = {
                        onSearch(it)
                        isSearchActive = false
                    },
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.search_text_field_height))
                        .width(dimensionResource(id = R.dimen.search_text_field_width))
                )
            } else {
                Text(
                    text = stringResource(id = currentScreen.titleRes),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        },
        navigationIcon = {
            if (currentScreen is AppDestinations.Details ||
                (isSearchActive && currentScreen is AppDestinations.Home)
            ) {
                IconButton(
                    onClick = {
                        if (currentScreen is AppDestinations.Details) {
                            navigateUp()
                        } else {
                            isSearchActive = false
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back_content_description)
                    )
                }
            }
        },
        actions = {
            if (currentScreen is AppDestinations.Home && !isSearchActive) {
                IconButton(onClick = { isSearchActive = true }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search_content_description)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = modifier
    )
}