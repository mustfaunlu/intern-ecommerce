package com.mustafaunlu.ecommerce.ui.detail

data class DetailProductUiData(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: List<String>,
    val rating: String,
)