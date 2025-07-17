package com.reynem.simplecook.api

data class RecipeDTO (
    val ingredients: String,
    val number: Int,
    val ranking: Int,
    val ignorePantry: Boolean,
) {
    init{
        require(ingredients.isNotBlank())
        require(number in 1..100)
        require(ranking in 1..2)
    }
}