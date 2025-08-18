package com.reynem.simplecook.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reynem.simplecook.api.models.ExtendedRecipe
import com.reynem.simplecook.api.models.Recipe
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val repository: RecipeRepository = RecipeRepository(ApiClient.apiService)

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _selectedRecipe = MutableLiveData<ExtendedRecipe>()
    val selectedRecipe: LiveData<ExtendedRecipe> = _selectedRecipe

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

    fun showSelectedRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val extendedRecipe = repository.getRecipeById(id)
                _selectedRecipe.postValue(extendedRecipe)
            } catch (e: Exception) {
                _error.postValue("Failed to fetch recipe: ${e.message}")
            }
        }
    }
}