package com.alpersevindik.data.helpers

import com.alpersevindik.data.model.NetworkResponse

fun <MODEL> NetworkResponse<MODEL>?.isSuccessful() = this != null && meta.code in 200..399