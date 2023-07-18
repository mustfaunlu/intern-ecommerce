package com.mustafaunlu.ecommerce.data.source.local

import com.mustafaunlu.ecommerce.data.database.CartDao
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val cartDao: CartDao) : LocalDataSource {
    override suspend fun getUserCartByUserIdFromDb(userId: Int): List<UserCartEntity> {
        return cartDao.getCartByUserId(userId)
    }

    override suspend fun insertUserCartToDb(userCartEntity: UserCartEntity) {
        cartDao.insertUserCart(userCartEntity)
    }

    override suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity) {
        cartDao.deleteUserCartItem(userCartEntity)
    }
}
