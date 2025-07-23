package com.reynem.simplecook.api

import com.reynem.simplecook.api.models.Recipe

class RecipeRepository (private val apiService: ApiService){
    // TODO: Maybe I should add cache system later
    suspend fun getRecipes(ingredients: String): List<Recipe>{
        return apiService.findRecipesByIngredients(ingredients)
    }
}