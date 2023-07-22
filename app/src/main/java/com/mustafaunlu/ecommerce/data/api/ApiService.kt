package com.mustafaunlu.ecommerce.data.api

import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.Products
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.data.dto.UserInfo
import com.mustafaunlu.ecommerce.data.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(PRODUCTS)
    suspend fun getProductsListFromApi(): Products

    @GET(SEARCH_PRODUCTS)
    suspend fun getProductsListBySearchFromApi(@Query("q") query: String): Products

    @GET(PRODUCTS_ID)
    suspend fun getSingleProductByIdFromApi(@Path(ID) productId: Int): Product

    @POST(AUTH_LOGIN)
    suspend fun postLoginRequest(@Body user: User): UserResponse

    @GET(PRODUCTS_CATEGORIES)
    suspend fun getAllCategoriesListFromApi(): List<String>

    @GET(PRODUCTS_CATEGORY)
    suspend fun getProductsListByCategoryNameFromApi(@Path(CATEGORY_NAME) categoryName: String): Products

    @GET(USERS)
    suspend fun getUserInformationByIdFromApi(@Path(ID) userId: Int): UserInfo

    companion object {
        const val PRODUCTS = "products"
        const val SEARCH_PRODUCTS = "products/search"
        const val ID = "id"
        const val PRODUCTS_ID = "products/{$ID}"
        const val AUTH_LOGIN = "auth/login"
        const val PRODUCTS_CATEGORIES = "products/categories"
        const val CATEGORY_NAME = "categoryName"
        const val PRODUCTS_CATEGORY = "products/category/{$CATEGORY_NAME}"
        const val USERS = "users/{$ID}"
    }
}
