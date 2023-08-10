package com.mustafaunlu.ecommerce.presentation.profile

data class FirebaseUserUiData(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val image: String,
    val password: String,
)