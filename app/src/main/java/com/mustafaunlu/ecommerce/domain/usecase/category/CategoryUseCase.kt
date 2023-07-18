package com.mustafaunlu.ecommerce.domain.usecase.category

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    operator fun invoke(): Flow<NetworkResponseState<List<String>>>

    operator fun invoke(categoryName: String): Flow<NetworkResponseState<List<AllProductsEntity>>>
}
