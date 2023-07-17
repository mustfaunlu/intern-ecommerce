package com.mustafaunlu.ecommerce.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "quantity")
    val quantity: Int = 1,
)
