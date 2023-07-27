package com.mustafaunlu.ecommerce.data.source.remote

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.Products
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.data.dto.UserInfo
import com.mustafaunlu.ecommerce.data.dto.UserResponse
import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getProductsListFromApi(): Flow<NetworkResponseState<Products>>

    fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<Product>>

    fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<Products>>

    fun postLoginRequest(user: User): Flow<NetworkResponseState<UserResponse>>

    fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>>

    fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<Products>>

    fun getUserInformationByIdFromApi(userId: Int): Flow<NetworkResponseState<UserInfo>>

    fun postSignUpRequest(user: UserSignUp): Flow<NetworkResponseState<UserSignUp>>
}
