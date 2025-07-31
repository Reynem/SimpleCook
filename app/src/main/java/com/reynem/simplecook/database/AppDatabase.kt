package com.reynem.simplecook.database

import android.content.Context
import androidx.room.*
import com.reynem.simplecook.database.models.IngredientC

@Database(entities = [IngredientC::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ingredientsDao(): IngredientsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}