package com.nicolascristaldo.foodrecipes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicolascristaldo.foodrecipes.data.database.model.RecipePreviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipePreviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipePreviewEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipePreviewEntity)
}