package com.mustafaunlu.ecommerce.domain.usecase.single

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleProductUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : GetSingleProductUseCase {
    override fun invoke(id: Int): Flow<NetworkResponseState<SingleProductEntity>> =
        repository.getSingleProductByIdFromApi(id)
}
