package com.mustafaunlu.ecommerce.presentation.favorite

data class FavoriteUiData(
    val userId: Int,
    val productId: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val imageUrl: String,
)