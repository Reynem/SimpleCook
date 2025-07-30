package com.reynem.simplecook.compound

import androidx.compose.runtime.mutableStateListOf

class IngredientsViewModel {
    private val _triggeredIngredients = mutableStateListOf<String>()
    val triggeredIngredients: List<String> get() = _triggeredIngredients

    fun toggleIngredient(ingredient: String){
        if (triggeredIngredients.contains(ingredient)){
            _triggeredIngredients.remove(ingredient)
        } else{
            _triggeredIngredients.add(ingredient)
        }
    }

//    fun clearIngredients(){
//        _triggeredIngredients.clear()
//    }
}