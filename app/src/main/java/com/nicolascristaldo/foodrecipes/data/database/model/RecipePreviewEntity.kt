package com.nicolascristaldo.foodrecipes.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicolascristaldo.foodrecipes.domain.model.recipe.Recipe

@Entity(tableName = "recipes")
data class RecipePreviewEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)

fun Recipe.toDatabase(): RecipePreviewEntity = RecipePreviewEntity(
    id = id,
    name = name,
    imageUrl = imageUrl
)
