package com.alpersevindik.data.model

data class NetworkResponse<MODEL>(
    val meta: NetworkResponseMeta,
    val response: MODEL
)

data class NetworkResponseMeta(
    val code: Int
)