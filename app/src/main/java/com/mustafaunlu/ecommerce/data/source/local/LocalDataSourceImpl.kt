package com.mustafaunlu.ecommerce.data.source.local

import com.mustafaunlu.ecommerce.data.database.AppDao
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDao: AppDao) : LocalDataSource {
    override suspend fun getUserCartByUserIdFromDb(userId: Int): List<UserCartEntity> {
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

    override suspend fun getFavoriteProductsFromDb(): List<FavoriteItemEntity> {
        return appDao.getFavoriteProducts()
    }

    override suspend fun insertFavoriteItemToDb(favoriteItemEntity: FavoriteItemEntity) {
        appDao.insertFavoriteItem(favoriteItemEntity)
    }

    override suspend fun deleteFavoriteItemFromDb(favoriteItemEntity: FavoriteItemEntity) {
        appDao.deleteFavoriteItem(favoriteItemEntity)
    }
}
