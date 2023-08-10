package com.mustafaunlu.ecommerce.domain.usecase.products

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : GetAllProductsUseCase {
    override fun invoke(): Flow<NetworkResponseState<List<ProductEntity>>> = repository.getProductsListFromApi()
}
