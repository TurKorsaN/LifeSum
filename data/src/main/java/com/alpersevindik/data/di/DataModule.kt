package com.alpersevindik.data.di

import android.content.Context
import androidx.room.Room
import com.alpersevindik.data.source.local.FoodDatabase
import com.alpersevindik.data.source.network.Constants
import com.alpersevindik.data.source.network.FoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideFoodService(retrofit: Retrofit): FoodService = retrofit.create(FoodService::class.java)

    @Provides
    @Singleton
    fun provideFoodDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, FoodDatabase::class.java, FoodDatabase.NAME).build()

    @Provides
    fun provideFoodDao(foodDB: FoodDatabase) = foodDB.foodDao()
}