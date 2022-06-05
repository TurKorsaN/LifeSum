package com.alpersevindik.lifesum.data.model

import com.alpersevindik.data.model.FoodItem

data class Food(
    var id: Int,
    val title: String,
    val quantity: String,
    val carbs: Float,
    val fiber: Float,
    val potassium: Float,
    val sodium: Float,
    val calories: Int,
    val fat: Float,
    val sugar: Float,
    val gramsPerServing: Float,
    val cholesterol: Float,
    val protein: Float,
    val unsaturatedFat: Float,
    val saturatedFat: Float,
)

fun FoodItem.toFood() = Food(
    id = id ?: -1,
    title = title,
    quantity= quantity,
    carbs = carbs,
    fiber = fiber,
    potassium = potassium,
    sodium = sodium,
    calories = calories,
    fat = fat,
    sugar = sugar,
    gramsPerServing = gramsPerServing,
    cholesterol = cholesterol,
    protein = protein,
    unsaturatedFat = unsaturatedFat,
    saturatedFat = saturatedFat
)