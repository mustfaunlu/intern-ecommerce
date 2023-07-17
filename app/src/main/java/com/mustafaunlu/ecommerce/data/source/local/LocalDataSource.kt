package com.mustafaunlu.ecommerce.data.source.local

import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

interface LocalDataSource {
    suspend fun getUserCartByUserIdFromDb(userId: Int): List<UserCartEntity>

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)

    suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity)
}
