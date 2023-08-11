package com.mustafaunlu.ecommerce.domain.usecase.product

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {
    operator fun invoke(): Flow<NetworkResponseState<List<ProductEntity>>>

    operator fun invoke(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>>
}
