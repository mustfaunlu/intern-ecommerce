package com.mustafaunlu.ecommerce.domain.usecase.product

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleProductUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : GetSingleProductUseCase {
    override fun invoke(id: Int): Flow<NetworkResponseState<DetailProductEntity>> =
        repository.getSingleProductByIdFromApi(id)
}
