package com.mustafaunlu.ecommerce.data.dto

data class UserResponse(
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val token: String,
    val username: String,
)
