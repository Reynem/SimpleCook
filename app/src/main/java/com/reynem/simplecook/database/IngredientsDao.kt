package com.reynem.simplecook.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.reynem.simplecook.database.models.IngredientC


@Dao
interface IngredientsDao {
    @Insert
    suspend fun insert(ingredient: IngredientC)

    @Query("SELECT * FROM ingredients")
    suspend fun getAll(): List<IngredientC>

    @Delete
    suspend fun delete(ingredient: IngredientC)
}