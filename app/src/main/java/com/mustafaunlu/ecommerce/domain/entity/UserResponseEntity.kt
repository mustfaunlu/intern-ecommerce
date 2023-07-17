package com.mustafaunlu.ecommerce.domain.entity

data class UserResponseEntity(
    val id: Int,
    val token: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val email: String,
)
