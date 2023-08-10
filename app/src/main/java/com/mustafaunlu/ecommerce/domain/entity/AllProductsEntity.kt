package com.mustafaunlu.ecommerce.domain.entity

data class AllProductsEntity(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val rating: Double,
)
