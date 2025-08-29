package com.reynem.simplecook.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryRecipe (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val image: String,
    val usedIngredients: String,
    val summary: String,
    val instructions: String,
    val readyInMinutes: Int,
    val servings: Int
)