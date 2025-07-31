package com.reynem.simplecook.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.reynem.simplecook.database.models.IngredientC
import kotlinx.coroutines.launch

class IngredientStorageViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val ingredientDao = db.ingredientsDao()
    
    fun insert(ingredient: IngredientC) {
        viewModelScope.launch{
            ingredientDao.insert(ingredient)
        }
    }

    fun initialization() {
        viewModelScope.launch {
            val current = ingredientDao.getAll()
            if (current.isEmpty()) {
                val defaultIngredients = DefaultIngredients.map { (nameRes, categoryRes) ->
                    IngredientC(
                        name = getApplication<Application>().getString(nameRes),
                        category = getApplication<Application>().getString(categoryRes))
                }
                ingredientDao.insertAll(defaultIngredients)
            }
        }
    }



    fun getAll(): LiveData<List<IngredientC>> = liveData{
        emit(ingredientDao.getAll())
    }
}