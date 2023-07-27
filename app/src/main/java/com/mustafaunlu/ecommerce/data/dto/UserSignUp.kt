package com.mustafaunlu.ecommerce.data.dto

import com.squareup.moshi.JsonClass

data class UserSignUp(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val phone: String,
)
