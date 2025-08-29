package com.reynem.simplecook.database

import android.content.Context
import androidx.room.*
import com.reynem.simplecook.database.models.*

@Database(
    entities = [IngredientC::class, HistoryRecipe::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [ AutoMigration(from = 1, to = 2) ]
)
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