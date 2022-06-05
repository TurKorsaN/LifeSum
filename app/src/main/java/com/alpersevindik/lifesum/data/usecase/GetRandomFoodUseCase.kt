package com.alpersevindik.lifesum.data.usecase

import com.alpersevindik.data.FoodRepository
import com.alpersevindik.lifesum.data.model.Food
import com.alpersevindik.lifesum.data.model.toFood
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class GetRandomFoodUseCase @Inject constructor(private val repository: FoodRepository) {

    companion object {
        private const val MIN = 1
        private const val MAX = 200
    }

    suspend fun getRandomFood(): Food? {
        val id = Random.nextInt(MIN, MAX)
        return repository.getFood(id)?.toFood()
    }
}