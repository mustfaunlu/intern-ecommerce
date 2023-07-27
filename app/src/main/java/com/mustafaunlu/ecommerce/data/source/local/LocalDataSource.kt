package com.mustafaunlu.ecommerce.data.source.local

import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

interface LocalDataSource {
    suspend fun getUserCartByUserIdFromDb(userId: Int): List<UserCartEntity>

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)

    suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity)

    suspend fun updateUserCartFromDb(userCartEntity: UserCartEntity)

    suspend fun getFavoriteProductsFromDb(): List<FavoriteItemEntity>

    suspend fun insertFavoriteItemToDb(favoriteItemEntity: FavoriteItemEntity)

    suspend fun deleteFavoriteItemFromDb(favoriteItemEntity: FavoriteItemEntity)
}
