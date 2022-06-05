package com.alpersevindik.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FoodItem(
    @PrimaryKey
    var id: Int?,
    val title: String,
    @SerializedName("pcstext")
    val quantity: String,
    val carbs: Float,
    val fiber: Float,
    val potassium: Float,
    val sodium: Float,
    val calories: Int,
    val fat: Float,
    val sugar: Float,
    @SerializedName("gramsperserving")
    val gramsPerServing: Float,
    val cholesterol: Float,
    val protein: Float,
    @SerializedName("unsaturatedfat")
    val unsaturatedFat: Float,
    @SerializedName("saturatedfat")
    val saturatedFat: Float,
)
