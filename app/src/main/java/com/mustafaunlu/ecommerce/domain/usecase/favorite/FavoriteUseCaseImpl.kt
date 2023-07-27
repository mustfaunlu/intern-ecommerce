package com.mustafaunlu.ecommerce.domain.usecase.favorite

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val repository: LocalRepository,
) : FavoriteUseCase {
    override suspend fun invoke(): Flow<NetworkResponseState<List<FavoriteItemEntity>>> =
        repository.getFavoriteProductsFromLocal()

    override suspend fun invoke(item: FavoriteItemEntity) {
        repository.insertFavoriteItemToDb(item)
    }
}
