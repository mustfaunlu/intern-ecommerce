package com.mustafaunlu.ecommerce.ui.home

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.productEntityList
import com.mustafaunlu.ecommerce.domain.usecase.product.SearchProductUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeSearchProductUseCase : SearchProductUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override operator fun invoke(query: String): Flow<NetworkResponseState<List<ProductEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)

            if (showError) {
                emit(NetworkResponseState.Error(IOException()))
            } else {
                emit(NetworkResponseState.Success(productEntityList))
            }
        }
}