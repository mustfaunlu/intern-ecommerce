package com.mustafaunlu.ecommerce.domain.entity.user

data class UserInformationEntity(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val image: String = "",
    val password: String,
    val token: String,
)