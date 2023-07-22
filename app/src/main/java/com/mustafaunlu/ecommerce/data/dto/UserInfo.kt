package com.mustafaunlu.ecommerce.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "address")
    val address: Address,
    @Json(name = "age")
    val age: Int,
    @Json(name = "bank")
    val bank: Bank,
    @Json(name = "birthDate")
    val birthDate: String,
    @Json(name = "bloodGroup")
    val bloodGroup: String,
    @Json(name = "company")
    val company: Company,
    @Json(name = "domain")
    val domain: String,
    @Json(name = "ein")
    val ein: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "eyeColor")
    val eyeColor: String,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "hair")
    val hair: Hair,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "ip")
    val ip: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "macAddress")
    val macAddress: String,
    @Json(name = "maidenName")
    val maidenName: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "ssn")
    val ssn: String,
    @Json(name = "university")
    val university: String,
    @Json(name = "userAgent")
    val userAgent: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "weight")
    val weight: Double,
)
