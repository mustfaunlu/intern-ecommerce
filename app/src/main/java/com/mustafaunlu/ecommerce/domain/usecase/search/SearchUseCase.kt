package com.mustafaunlu.ecommerce.domain.usecase.search

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    operator fun invoke(query: String): Flow<NetworkResponseState<List<AllProductsEntity>>>
}
