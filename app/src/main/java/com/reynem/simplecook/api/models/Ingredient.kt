package com.reynem.simplecook.api.models

data class Ingredient (
    val aisle: String,
    val amount: Double,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val name: String,
    val original: String,
    val unit: String,
){
}