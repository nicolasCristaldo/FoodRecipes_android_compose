package com.nicolascristaldo.foodrecipes.data.providers

import com.nicolascristaldo.foodrecipes.R

object CategoryImagesProvider {
    fun getImageByCategory(category: String): Int {
        return when(category) {
            "Beef" -> R.drawable.beef
            "Chicken" -> R.drawable.chicken
            "Dessert" -> R.drawable.dessert
            "Lamb" -> R.drawable.lamb
            "Pork" -> R.drawable.pork
            "Breakfast" -> R.drawable.breakfast
            "Goat" -> R.drawable.goat
            "Pasta" -> R.drawable.pasta
            "Seafood" -> R.drawable.seafood
            "Side" -> R.drawable.side
            "Starter" -> R.drawable.starter
            "Miscellaneous" -> R.drawable.miscellaneous
            "Vegan" -> R.drawable.vegan
            else -> R.drawable.vegetarian
        }
    }
}