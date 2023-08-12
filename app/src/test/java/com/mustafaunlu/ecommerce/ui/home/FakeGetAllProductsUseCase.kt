package com.mustafaunlu.ecommerce.ui.home

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.usecase.product.GetAllProductsUseCase
import com.mustafaunlu.ecommerce.ui.productEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeGetAllProductsUseCase : GetAllProductsUseCase {

    private var showError = false

    fun setShowError(value: Boolean) {
        showError = value
    }
    override fun invoke(): Flow<NetworkResponseState<List<ProductEntity>>> = flow {
        emit(NetworkResponseState.Loading)
        if (showError) {
            emit(NetworkResponseState.Error(IOException()))
        } else {
            emit(NetworkResponseState.Success(productEntityList))
        }
    }

    override fun invoke(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>> = flow {
        emit(NetworkResponseState.Loading)
        if (showError) {
            emit(NetworkResponseState.Error(IOException()))
        } else {
            emit(NetworkResponseState.Success(productEntityList))
        }
    }
}