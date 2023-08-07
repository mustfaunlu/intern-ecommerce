package com.mustafaunlu.ecommerce.presentation.cart

data class UserCartUiData(
    val userId: Int,
    val productId: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val imageUrl: String,
)