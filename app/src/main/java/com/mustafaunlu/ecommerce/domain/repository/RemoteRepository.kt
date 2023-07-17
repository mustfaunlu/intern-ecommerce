package com.mustafaunlu.ecommerce.domain.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.Products
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    fun getProductsListFromApi(): Flow<NetworkResponseState<List<AllProductsEntity>>>

    fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<SingleProductEntity>>

    fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<List<AllProductsEntity>>>

    fun postLoginRequest(user: User): Flow<NetworkResponseState<UserResponseEntity>>

    fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>>

    fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>>
}