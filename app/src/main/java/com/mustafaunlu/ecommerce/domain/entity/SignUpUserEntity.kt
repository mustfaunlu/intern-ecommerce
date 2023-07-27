package com.mustafaunlu.ecommerce.domain.entity

data class SignUpUserEntity(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
)
