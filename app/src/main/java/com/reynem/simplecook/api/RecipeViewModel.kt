package com.reynem.simplecook.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reynem.simplecook.api.models.Recipe
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val repository: RecipeRepository = RecipeRepository(ApiClient.apiService)

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchRecipes(ingredients: String) {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes(ingredients)
                _recipes.postValue(recipes)
            } catch (e: Exception) {
                _error.postValue("Failed to fetch recipes: ${e.message}")
            }
        }
    }
}