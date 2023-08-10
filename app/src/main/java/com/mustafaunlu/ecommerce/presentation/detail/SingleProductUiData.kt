package com.mustafaunlu.ecommerce.presentation.detail

data class SingleProductUiData(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: List<String>,
    val rating: String,
)