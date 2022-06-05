package com.alpersevindik.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alpersevindik.data.model.FoodItem
import com.alpersevindik.data.source.local.dao.FoodDao

@Database(entities = [FoodItem::class], version = 1)
internal abstract class FoodDatabase: RoomDatabase() {
    companion object {
        const val NAME = "FoodDB"
    }

    abstract fun foodDao(): FoodDao
}