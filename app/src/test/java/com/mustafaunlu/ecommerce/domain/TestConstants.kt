package com.mustafaunlu.ecommerce.domain

import androidx.annotation.VisibleForTesting
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity

@VisibleForTesting
const val categoryName: String = "smartphones"

@VisibleForTesting
const val productId: Int = 1

@VisibleForTesting
const val query: String = "trend"

@VisibleForTesting
val detailProductEntity =
    DetailProductEntity(
        1,
        "iPhone 9",
        "An apple mobile which is nothing like apple",
        "549",
        listOf(
            "https://i.dummyjson.com/data/products/1/1.jpg",
            "https://i.dummyjson.com/data/products/1/2.jpg",
            "https://i.dummyjson.com/data/products/1/3.jpg",
            "https://i.dummyjson.com/data/products/1/4.jpg",
            "https://i.dummyjson.com/data/products/1/thumbnail.jpg"
        ),
        "4.69"
    )

@VisibleForTesting
val productEntity =
    ProductEntity(
        2,
        "iPhone 10",
        "An apple mobile which is nothing like apple",
        "549",
        "https://i.dummyjson.com/data/products/1/4.jpg",
        4.69
    )

@VisibleForTesting
val productEntityList = listOf(
    productEntity,
)