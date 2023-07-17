package com.mustafaunlu.ecommerce.domain.entity

data class SingleProductEntity(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val rating: String,
)
