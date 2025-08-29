package com.reynem.simplecook.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.reynem.simplecook.database.models.*


@Dao
interface IngredientsDao {
    @Insert
    suspend fun insert(ingredient: IngredientC)

    @Insert
    suspend fun insertAll(ingredients: List<IngredientC>)

    @Query("SELECT * FROM ingredients")
    suspend fun getAll(): List<IngredientC>

    @Delete
    suspend fun delete(ingredient: IngredientC)

    @Query("SELECT * FROM history")
    suspend fun getWholeHistory(): List<HistoryRecipe>

    @Insert
    suspend fun insertHistoryRecipe(historyRecipe: HistoryRecipe)

    @Delete
    suspend fun deleteHistoryRecipe(historyRecipe: HistoryRecipe)
}