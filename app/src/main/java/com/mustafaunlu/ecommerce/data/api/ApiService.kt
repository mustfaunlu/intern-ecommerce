package com.mustafaunlu.ecommerce.data.api

import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(PRODUCTS)
    suspend fun getProductsListFromApi(): Products

    @GET(SEARCH_PRODUCTS)
    suspend fun getProductsListBySearchFromApi(@Query("q") query: String): Products

    @GET(PRODUCTS_ID)
    suspend fun getSingleProductByIdFromApi(@Path(ID) productId: Int): Product

    @GET(PRODUCTS_CATEGORIES)
    suspend fun getAllCategoriesListFromApi(): List<String>

    @GET(PRODUCTS_CATEGORY)
    suspend fun getProductsListByCategoryNameFromApi(@Path(CATEGORY_NAME) categoryName: String): Products

    companion object {
        private const val PRODUCTS = "products"
        private const val SEARCH_PRODUCTS = "products/search"
        private const val ID = "id"
        private const val PRODUCTS_ID = "products/{$ID}"
        private const val PRODUCTS_CATEGORIES = "products/categories"
        private const val CATEGORY_NAME = "categoryName"
        private const val PRODUCTS_CATEGORY = "products/category/{$CATEGORY_NAME}"
    }
}
