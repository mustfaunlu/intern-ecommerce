package com.mustafaunlu.ecommerce.ui

import androidx.annotation.VisibleForTesting
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.ui.detail.DetailProductUiData
import com.mustafaunlu.ecommerce.ui.home.ProductUiData

/**
 * HomeFragment
 */
@VisibleForTesting
val allProductEntity = ProductEntity(
    2,
    "iPhone 10",
    "An apple mobile which is nothing like apple",
    "549",
    "https://i.dummyjson.com/data/products/1/4.jpg",
    4.69
)

@VisibleForTesting
val productEntityList = listOf(
    allProductEntity,
)

@VisibleForTesting
val productHomeUiData = ProductUiData(
    2,
    "iPhone 10",
    "An apple mobile which is nothing like apple",
    "549",
    "https://i.dummyjson.com/data/products/1/4.jpg",
    4.69
)

@VisibleForTesting
val productHomeUiDataList = listOf(
    productHomeUiData,
)

/**
 * ProductDetailFragment
 */

@VisibleForTesting
val detailProductEntity = DetailProductEntity(
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
val detailProductUiData = DetailProductUiData(
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
const val productId = 1

@VisibleForTesting
const val idArg = "productId"