package com.reynem.simplecook.api

class RecipeRepository (private val apiService: ApiService){
    // TODO: Maybe I should add cache system later
    suspend fun getRecipes(ingredients: String): List<Recipe>{
        return apiService.findRecipesByIngredients(ingredients)
    }
}