package com.mustafaunlu.ecommerce.domain.usecase.product

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.productEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeGetAllProductUseCase : GetAllProductsUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }
    override operator fun invoke(): Flow<NetworkResponseState<List<ProductEntity>>> = flow {
        emit(NetworkResponseState.Loading)
        if (showError) {
            emit(NetworkResponseState.Error(IOException()))
        } else {
            emit(NetworkResponseState.Success(productEntityList))
        }
    }

    override operator fun invoke(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>> = flow {
        emit(NetworkResponseState.Loading)
        if (showError) {
            emit(NetworkResponseState.Error(IOException()))
        } else {
            emit(NetworkResponseState.Success(productEntityList))
        }
    }
}