package com.alpersevindik.data

import com.alpersevindik.data.helpers.isSuccessful
import com.alpersevindik.data.model.FoodItem
import com.alpersevindik.data.source.local.dao.FoodDao
import com.alpersevindik.data.source.network.FoodService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val foodService: FoodService,
    private val foodDao: FoodDao
) {

    suspend fun getFood(id: Int): FoodItem? {
        val networkResult = runCatching { foodService.getFood(id = id) }
            .onFailure { it.printStackTrace() }
            .getOrNull()
        val foodItem: FoodItem?
        if (networkResult.isSuccessful()) {
            foodItem = networkResult!!.response.apply { this.id = id }
            foodDao.insert(foodItem)
        } else {
            foodItem = foodDao.getFood(id)
        }
        return foodItem
    }
}