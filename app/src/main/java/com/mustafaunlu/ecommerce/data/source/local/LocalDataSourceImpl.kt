package com.mustafaunlu.ecommerce.data.source.local

import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

class LocalDataSourceImpl : LocalDataSource {
    override suspend fun getUserCartByUserIdFromDb(userId: Int): List<UserCartEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUserCartToDb(userCartEntity: UserCartEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity) {
        TODO("Not yet implemented")
    }
}
