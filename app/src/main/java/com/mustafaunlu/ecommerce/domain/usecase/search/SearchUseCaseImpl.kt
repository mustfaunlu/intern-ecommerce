package com.mustafaunlu.ecommerce.domain.usecase.search

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : SearchUseCase {
    override fun invoke(query: String): Flow<NetworkResponseState<List<AllProductsEntity>>> {
        return repository.getProductsListBySearchFromApi(query)
    }
}
