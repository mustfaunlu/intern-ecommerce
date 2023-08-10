package com.mustafaunlu.ecommerce.data.source.local

import com.mustafaunlu.ecommerce.data.database.AppDao
import com.mustafaunlu.ecommerce.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDao: AppDao) : LocalDataSource {
    override suspend fun getUserCartByUserIdFromDb(userId: String): List<UserCartEntity> {
        return appDao.getCartByUserId(userId)
    }

    override suspend fun insertUserCartToDb(userCartEntity: UserCartEntity) {
        appDao.insertUserCart(userCartEntity)
    }

    override suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity) {
        appDao.deleteUserCartItem(userCartEntity)
    }

    override suspend fun updateUserCartFromDb(userCartEntity: UserCartEntity) {
        appDao.updateUserCartItem(userCartEntity)
    }

    override suspend fun getFavoriteProductsFromDb(userId: String): List<FavoriteProductEntity> {
        return appDao.getFavoriteProducts(userId)
    }

    override suspend fun insertFavoriteItemToDb(favoriteProductEntity: FavoriteProductEntity) {
        appDao.insertFavoriteItem(favoriteProductEntity)
    }

    override suspend fun deleteFavoriteItemFromDb(favoriteProductEntity: FavoriteProductEntity) {
        appDao.deleteFavoriteItem(favoriteProductEntity)
    }

    override suspend fun getUserCartBadgeStateFromDb(userUniqueInfo: String): UserCartBadgeEntity {
        return appDao.getUserBadge(userUniqueInfo)
    }

    override suspend fun insertUserCartBadgeCountToDb(userBadge: UserCartBadgeEntity) {
        appDao.insertUserBadge(userBadge)
    }
}
