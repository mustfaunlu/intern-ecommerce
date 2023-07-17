package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

class RemoteRepositoryImpl : RemoteRepository {
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
        TODO("Not yet implemented")
    }

    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        TODO("Not yet implemented")
    }

    override fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>> {
        TODO("Not yet implemented")
    }
}
