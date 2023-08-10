package com.mustafaunlu.ecommerce.domain.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun getCartsByUserIdFromDb(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>>

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)

    suspend fun deleteUserCart(userCartEntity: UserCartEntity)

    suspend fun updateUserCart(userCartEntity: UserCartEntity)

    suspend fun getFavoriteProductsFromDb(userId: String): Flow<NetworkResponseState<List<FavoriteProductEntity>>>

    suspend fun insertFavoriteProductToDb(favoriteProductEntity: FavoriteProductEntity)

    suspend fun deleteFavoriteProduct(favoriteProductEntity: FavoriteProductEntity)

    suspend fun getUserCartBadgeStateFromDb(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>>

    suspend fun insertUserCartBadgeStateToDb(userBadge: UserCartBadgeEntity)
}
