package com.mustafaunlu.ecommerce.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserCartResponse(
    @Json(name = "carts")
    val carts: List<CartResponse>,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "skip")
    val skip: Int,
    @Json(name = "total")
    val total: Int,
)
