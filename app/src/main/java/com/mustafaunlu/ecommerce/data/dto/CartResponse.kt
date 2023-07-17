package com.mustafaunlu.ecommerce.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponse(
    @Json(name = "discountedTotal")
    val discountedTotal: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "products")
    val products: List<CartResponseProduct>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "totalProducts")
    val totalProducts: Int,
    @Json(name = "totalQuantity")
    val totalQuantity: Int,
    @Json(name = "userId")
    val userId: Int,
)
