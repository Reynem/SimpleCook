package com.reynem.simplecook.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // Maybe I will switch to complexSearch later
    @GET("recipes/findByIngredients")

    fun getRecipes(): Call<RecipeDTO>
}