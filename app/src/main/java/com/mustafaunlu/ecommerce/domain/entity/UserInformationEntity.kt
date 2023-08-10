package com.mustafaunlu.ecommerce.domain.entity

data class UserInformationEntity(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val image: String = "",
    val password: String,
)