package com.nicolascristaldo.foodrecipes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.nicolascristaldo.foodrecipes.R

@Composable
fun MessageScreen(
    message: String,
    icon: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.message_screen_icon_size))
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium_size)))

        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
    }
}