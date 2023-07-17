package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl : LocalRepository {
    override fun getCartsByUserIdFromLocal(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCartToDb(userCartEntity: UserCartEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserCartItem(userCartEntity: UserCartEntity) {
        TODO("Not yet implemented")
    }
}
