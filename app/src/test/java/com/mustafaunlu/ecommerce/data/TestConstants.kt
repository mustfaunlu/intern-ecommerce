package com.mustafaunlu.ecommerce.data

import androidx.annotation.VisibleForTesting
import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.Products
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity

const val ALL_PRODUCTS_RESPONSE_FILE_NAME = "all_products_response.json"
const val SINGLE_PRODUCT_RESPONSE_FILE_NAME = "SingleProductResponse.json"
const val SEARCH_RESPONSE_FILE_NAME = "SearchProductResponse.json"
const val CATEGORY_PRODUCTS_RESPONSE_FILE_NAME = "ProductsOfCategoryResponse.json"

@VisibleForTesting
const val categoryName: String = "smartphones"

@VisibleForTesting
const val productId: Int = 1

@VisibleForTesting
const val query: String = "trend"

@VisibleForTesting
val singleProduct = Product(
    brand = "Apple",
    category = "smartphones",
    description = "",
    discountPercentage = 0.0,
    id = 1,
    images = listOf(
        "https://i.dummyjson.com/data/products/1/1.jpg",
        "https://i.dummyjson.com/data/products/1/2.jpg",
        "https://i.dummyjson.com/data/products/1/3.jpg",
        "https://i.dummyjson.com/data/products/1/4.jpg",
        "https://i.dummyjson.com/data/products/1/thumbnail.jpg"
    ),
    price = 1000,
    rating = 4.5,
    stock = 10,
    thumbnail = "",
    title = "iPhone 12 Pro Max"
)

@VisibleForTesting
val allProducts = Products(
    limit = 30,
    total = 100,
    skip = 0,
    products = listOf(singleProduct)
)

@VisibleForTesting
val productList = listOf(singleProduct)

@VisibleForTesting
const val SERVER_PORT = 8000

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