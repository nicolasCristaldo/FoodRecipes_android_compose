package com.nicolascristaldo.foodrecipes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicolascristaldo.foodrecipes.data.database.model.RecipePreviewEntity

@Database(
    entities = [RecipePreviewEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}