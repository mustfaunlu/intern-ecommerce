package com.mustafaunlu.ecommerce.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_carts")
data class UserCartEntity(
    val userId: Int,
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productId: Int,
    @ColumnInfo(name = "product_price") val price: Int,
    @ColumnInfo(name = "product_quantity") val quantity: Int,
    @ColumnInfo(name = "product_title") val title: String,
    @ColumnInfo(name = "product_image") val image: String,
)
