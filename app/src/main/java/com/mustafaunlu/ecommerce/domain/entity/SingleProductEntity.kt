package com.mustafaunlu.ecommerce.domain.entity

data class SingleProductEntity(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: List<String>,
    val rating: String,
)
