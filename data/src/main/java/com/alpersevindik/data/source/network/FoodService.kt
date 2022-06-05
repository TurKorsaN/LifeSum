package com.alpersevindik.data.source.network

import com.alpersevindik.data.model.FoodItem
import com.alpersevindik.data.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {

    @GET(Constants.ENDPOINT_GET_FOOD)
    suspend fun getFood(
//        @Header(Constants.HEADER_AUTHORIZATION) authorization: String = Constants.HEADER_AUTHORIZATION_VALUE,
        @Query(Constants.PARAM_FOOD_ID) id: Int
    ): NetworkResponse<FoodItem>
}