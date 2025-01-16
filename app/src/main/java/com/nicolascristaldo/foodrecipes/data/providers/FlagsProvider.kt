package com.nicolascristaldo.foodrecipes.data.providers

import com.nicolascristaldo.foodrecipes.R

class FlagsProvider {
    fun getFlagByArea(area: String): Int {
        return when(area) {
            "American" -> R.drawable.usa_flag
            "British" -> R.drawable.gb_flag
            "Canadian" -> R.drawable.canada_flag
            "Chinese" -> R.drawable.china_flag
            "Dutch" -> R.drawable.netherlands_flag
            "Egyptian" -> R.drawable.egypt_flag
            "Filipino" -> R.drawable.philippines_flag
            "French" -> R.drawable.france_flag
            "Greek" -> R.drawable.greece_flag
            "Indian" -> R.drawable.india_flag
            "Italian" -> R.drawable.italy_flag
            "Japanese" -> R.drawable.japan_flag
            "Kenyan" -> R.drawable.kenya_flag
            "Malaysian" -> R.drawable.malaysia_flag
            "Mexican" -> R.drawable.mexico_flag
            "Moroccan" -> R.drawable.morocco_flag
            "Polish" -> R.drawable.poland_flag
            "Portuguese" -> R.drawable.portugal_flag
            "Russian" -> R.drawable.russia_flag
            "Spanish" -> R.drawable.spain_flag
            "Tunisian" -> R.drawable.tunisia_flag
            "Vietnamese" -> R.drawable.vietnam_flag
            "Ukrainian" -> R.drawable.ukraine_flag
            "Croatian" -> R.drawable.croatia_flag
            "Jamaican" -> R.drawable.jamaica_flag
            "Turkish" -> R.drawable.turkey_flag
            "Thai" -> R.drawable.thailand_flag
            "Irish" -> R.drawable.ireland_flag
            else -> 0
        }
    }
}