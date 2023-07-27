package com.mustafaunlu.ecommerce.domain.usecase.favorite

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    suspend operator fun invoke(): Flow<NetworkResponseState<List<FavoriteItemEntity>>> // getFavoriteProductsFromLocal

    suspend operator fun invoke(item: FavoriteItemEntity) // insertFavoriteItemToDb
}
