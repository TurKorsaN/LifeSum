package com.alpersevindik.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alpersevindik.data.model.FoodItem

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: FoodItem)

    @Query("SELECT * FROM fooditem")
    fun getAll(): List<FoodItem>

    @Query("SELECT * FROM fooditem WHERE id = :id")
    fun getFood(id: Int): FoodItem?
}