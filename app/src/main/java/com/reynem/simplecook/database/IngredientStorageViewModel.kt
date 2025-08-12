package com.reynem.simplecook.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.reynem.simplecook.database.models.IngredientC
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

//    fun getAll(): LiveData<List<IngredientC>> = liveData {
//        try {
//            Log.d("IngredientStorageVM", "Getting all ingredients")
//            val ingredients = withContext(Dispatchers.IO) {
//                ingredientDao.getAll()
//            }
//            Log.d("IngredientStorageVM", "Retrieved ${ingredients.size} ingredients")
//            emit(ingredients)
//        } catch (e: Exception) {
//            Log.e("IngredientStorageVM", "Error getting all ingredients", e)
//            emit(emptyList())
//        }
//    }

    fun getAllByCategories(): LiveData<Map<String, List<String>>> = liveData {
        try {
            Log.d("IngredientStorageVM", "Getting all ingredients by categories")
            val ingredients = withContext(Dispatchers.IO) {
                ingredientDao.getAll()
            }

            val categories = ingredients.groupBy(
                keySelector = { it.category },
                valueTransform = { it.name })

            emit(categories)
        } catch(e: Exception){
            Log.e("IngredientStorageVM", "Error getting all ingredients by categories", e)
            emit(emptyMap())
        }
    }
}