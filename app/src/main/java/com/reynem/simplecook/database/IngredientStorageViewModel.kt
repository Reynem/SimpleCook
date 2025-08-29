package com.reynem.simplecook.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.reynem.simplecook.database.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IngredientStorageViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val ingredientDao = db.ingredientsDao()

//    fun insert(ingredient: IngredientC) {
//        viewModelScope.launch {
//            try {
//                ingredientDao.insert(ingredient)
//                Log.d("IngredientStorageVM", "Ingredient inserted: ${ingredient.name}")
//            } catch (e: Exception) {
//                Log.e("IngredientStorageVM", "Error inserting ingredient", e)
//            }
//        }
//    }

    fun initialization() {
        viewModelScope.launch {
            try {
                Log.d("IngredientStorageVM", "Starting database initialization")

                val current = withContext(Dispatchers.IO) {
                    ingredientDao.getAll()
                }

                Log.d("IngredientStorageVM", "Current ingredients count: ${current.size}")

                if (current.isEmpty()) {
                    Log.d("IngredientStorageVM", "Database is empty, inserting default ingredients")

                    val defaultIngredients = DefaultIngredients.map { (nameRes, categoryRes) ->
                        try {
                            IngredientC(
                                name = getApplication<Application>().getString(nameRes),
                                category = getApplication<Application>().getString(categoryRes)
                            )
                        } catch (e: Exception) {
                            Log.e("IngredientStorageVM", "Error creating ingredient with resource $nameRes", e)
                            null
                        }
                    }.filterNotNull()

                    Log.d("IngredientStorageVM", "Created ${defaultIngredients.size} default ingredients")

                    withContext(Dispatchers.IO) {
                        ingredientDao.insertAll(defaultIngredients)
                    }

                    Log.d("IngredientStorageVM", "Default ingredients inserted successfully")
                } else {
                    Log.d("IngredientStorageVM", "Database already contains ingredients")
                }

            } catch (e: Exception) {
                Log.e("IngredientStorageVM", "Error during initialization", e)
            }
        }
    }

    fun getAllByCategories(): LiveData<Map<String, List<String>>> = liveData {
        try {
            Log.d("IngredientStorageVM", "Getting all ingredients by categories")
            val ingredients = withContext(Dispatchers.IO) {
                ingredientDao.getAll()
            }

            val categories = withContext(Dispatchers.IO) {
                ingredients.groupBy(
                    keySelector = { it.category },
                    valueTransform = { it.name })
            }

            emit(categories)
        } catch(e: Exception){
            Log.e("IngredientStorageVM", "Error getting all ingredients by categories", e)
            emit(emptyMap())
        }
    }

    fun insertHistoryRecipe(historyRecipe: HistoryRecipe) {
        viewModelScope.launch {
            try {
                ingredientDao.insertHistoryRecipe(historyRecipe)
            } catch(e: Exception) {
                Log.e("IngredientStorageVM", "Error inserting history recipe")
            }
        }
    }

    fun getWholeHistory(): LiveData<List<HistoryRecipe>> = liveData {
        try {
            val history = withContext(Dispatchers.IO) {
                ingredientDao.getWholeHistory()
            }

            emit(history)
        } catch(e: Exception) {
            Log.e("IngredientStorageVM", "Error getting whole history")
        }
    }

    fun deleteHistoryRecipe(historyRecipe: HistoryRecipe) {
        viewModelScope.launch {
            try {
                ingredientDao.deleteHistoryRecipe(historyRecipe)
            } catch (e: Exception) {
                Log.e("IngredientStorageVM", "Error deleting history recipe")
            }
        }
    }
}