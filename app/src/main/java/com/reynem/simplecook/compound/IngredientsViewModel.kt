package com.reynem.simplecook.compound

import androidx.compose.runtime.mutableStateSetOf
import androidx.lifecycle.ViewModel

class IngredientsViewModel : ViewModel() {
    private val _triggeredIngredients = mutableStateSetOf<String>()
    val triggeredIngredients: Set<String> get() = _triggeredIngredients

    fun toggleIngredient(ingredient: String){
        if (triggeredIngredients.contains(ingredient)){
            _triggeredIngredients.remove(ingredient)
        } else{
            _triggeredIngredients.add(ingredient)
        }
    }

    fun isIngredientTriggered(ingredient: String): Boolean{
        return _triggeredIngredients.contains(ingredient)
    }

    fun getTriggeredIngredients(): String {
        return _triggeredIngredients.joinToString(",")
    }



//    fun clearIngredients(){
//        _triggeredIngredients.clear()
//    }
}