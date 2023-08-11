package com.mustafaunlu.ecommerce.domain.usecase.product

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.detailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeGetSingleProductUseCase : GetSingleProductUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }
    override operator fun invoke(id: Int): Flow<NetworkResponseState<DetailProductEntity>> = flow {
        emit(NetworkResponseState.Loading)
        if (showError) {
            emit(NetworkResponseState.Error(IOException()))
        } else {
            emit(NetworkResponseState.Success(detailProductEntity))
        }
    }
}