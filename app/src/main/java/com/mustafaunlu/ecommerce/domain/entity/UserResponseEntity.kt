package com.mustafaunlu.ecommerce.domain.entity

data class UserResponseEntity(
    val id: Int,
    val token: String,
    val username: String,
    val image: String,
)
