package com.mustafaunlu.ecommerce.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponseProduct(
    @Json(name = "discountPercentage")
    val discountPercentage: Double,
    @Json(name = "discountedPrice")
    val discountedPrice: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "price")
    val price: Int,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "total")
    val total: Int,
)
