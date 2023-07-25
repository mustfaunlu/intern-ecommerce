package com.mustafaunlu.ecommerce.data.dto

data class User(
    val username: String,
    val password: String,
    val expiresInMins: String = "1",
)
