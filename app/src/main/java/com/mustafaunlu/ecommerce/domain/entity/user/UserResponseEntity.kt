package com.mustafaunlu.ecommerce.domain.entity.user

data class UserResponseEntity(
    val id: Int,
    val token: String,
    val username: String,
)
