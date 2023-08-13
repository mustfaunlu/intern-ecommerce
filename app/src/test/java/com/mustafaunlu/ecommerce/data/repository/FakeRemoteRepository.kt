package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.allProductEntities
import com.mustafaunlu.ecommerce.data.detailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeRemoteRepository : RemoteRepository {

    private var showErrorForAllProducts = false
    private var showErrorForSingleProduct = false

    fun setShowErrorForAllProducts(value: Boolean) {
        showErrorForAllProducts = value
    }

    fun setShowErrorForSingleProduct(value: Boolean) {
        showErrorForSingleProduct = value
    }

    override fun getProductsListFromApi(): Flow<NetworkResponseState<List<ProductEntity>>> = flow {
        emit(NetworkResponseState.Loading)
        if (showErrorForAllProducts) {
            emit(NetworkResponseState.Error(IOException()))
        } else {
            emit(NetworkResponseState.Success(allProductEntities))
        }
    }

    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<DetailProductEntity>> =
        flow {
            emit(NetworkResponseState.Loading)
            if (showErrorForSingleProduct) {
                emit(NetworkResponseState.Error(IOException()))
            } else {
                emit(NetworkResponseState.Success(detailProductEntity))
            }
        }

    override fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<List<ProductEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            if (showErrorForAllProducts) {
                emit(NetworkResponseState.Error(IOException()))
            } else {
                emit(NetworkResponseState.Success(allProductEntities))
            }
        }

    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        TODO("Not yet implemented")
    }

    override fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>> {
        return flow {
            emit(NetworkResponseState.Loading)
            if (showErrorForAllProducts) {
                emit(NetworkResponseState.Error(IOException()))
            } else {
                emit(NetworkResponseState.Success(allProductEntities))
            }
        }
    }
}