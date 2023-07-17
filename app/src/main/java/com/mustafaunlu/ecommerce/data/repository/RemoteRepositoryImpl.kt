package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.data.dto.UserResponse
import com.mustafaunlu.ecommerce.data.source.remote.RemoteDataSource
import com.mustafaunlu.ecommerce.di.coroutine.IoDispatcher
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val userResponseEntityMapper: ProductBaseMapper<UserResponse, UserResponseEntity>,
) : RemoteRepository {
    override fun getProductsListFromApi(): Flow<NetworkResponseState<List<AllProductsEntity>>> {
        TODO("Not yet implemented")
    }

    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<SingleProductEntity>> {
        TODO("Not yet implemented")
    }

    override fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<List<AllProductsEntity>>> {
        TODO("Not yet implemented")
    }

    override fun postLoginRequest(user: User): Flow<NetworkResponseState<UserResponseEntity>> {
        return remoteDataSource.postLoginRequest(user).map {
            when (it) {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(userResponseEntityMapper.map(it.result))
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(ioDispatcher)
    }

    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        TODO("Not yet implemented")
    }

    override fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>> {
        TODO("Not yet implemented")
    }
}
