package com.reynem.simplecook.api

import com.reynem.simplecook.api.models.ExtendedRecipe
import com.reynem.simplecook.api.models.Recipe
import com.reynem.simplecook.utils.RecipeConstants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface ApiService {
    // TODO: Maybe I will switch to complexSearch later
    @GET("recipes/findByIngredients")
    suspend fun findRecipesByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int = RecipeConstants.NUMBER_OF_RECIPES
    ): List<Recipe>

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,
        @Query("addWinePairing") addWinePairing: Boolean = false,
        @Query("addTasteData") addTasteData: Boolean = false,
    ): ExtendedRecipe

}