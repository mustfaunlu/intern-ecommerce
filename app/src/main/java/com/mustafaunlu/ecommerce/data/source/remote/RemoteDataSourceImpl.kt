package com.mustafaunlu.ecommerce.data.source.remote

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.Products
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.data.dto.UserResponse
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl : RemoteDataSource {
    override fun getProductsListFromApi(): Flow<NetworkResponseState<Products>> {
        TODO("Not yet implemented")
    }

    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<Product>> {
        TODO("Not yet implemented")
    }

    override fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<Products>> {
        TODO("Not yet implemented")
    }

    override fun postLoginRequest(user: User): Flow<NetworkResponseState<UserResponse>> {
        TODO("Not yet implemented")
    }

    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        TODO("Not yet implemented")
    }

    override fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<Products>> {
        TODO("Not yet implemented")
    }
}
