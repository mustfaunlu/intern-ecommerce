package com.mustafaunlu.ecommerce.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartRequest(
    @Json(name = "products")
    val products: List<CartItem>,
    @Json(name = "userId")
    val userId: Int = 1,
)
