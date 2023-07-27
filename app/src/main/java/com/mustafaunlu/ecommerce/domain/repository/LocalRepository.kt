package com.mustafaunlu.ecommerce.domain.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun getCartsByUserIdFromLocal(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>>

    suspend fun insertCartToDb(userCartEntity: UserCartEntity)

    suspend fun deleteUserCartItem(userCartEntity: UserCartEntity)

    suspend fun updateUserCartItem(userCartEntity: UserCartEntity)

    suspend fun getFavoriteProductsFromLocal(): Flow<NetworkResponseState<List<FavoriteItemEntity>>>

    suspend fun insertFavoriteItemToDb(favoriteItemEntity: FavoriteItemEntity)

    suspend fun deleteFavoriteItem(favoriteItemEntity: FavoriteItemEntity)
}
