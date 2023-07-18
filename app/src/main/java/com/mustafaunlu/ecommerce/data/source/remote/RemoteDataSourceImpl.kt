package com.mustafaunlu.ecommerce.data.source.remote

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.api.ApiService
import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.Products
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.data.dto.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource {
    override fun getProductsListFromApi(): Flow<NetworkResponseState<Products>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getProductsListFromApi()
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<Product>> {
        TODO("Not yet implemented")
    }

    override fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<Products>> {
        TODO("Not yet implemented")
    }

    override fun postLoginRequest(user: User): Flow<NetworkResponseState<UserResponse>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.postLoginRequest(user)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getAllCategoriesListFromApi()
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<Products>> {
        TODO("Not yet implemented")
    }
}
