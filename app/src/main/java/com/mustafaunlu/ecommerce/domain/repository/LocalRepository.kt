package com.mustafaunlu.ecommerce.domain.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getCartsByUserIdFromLocal(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>>

    suspend fun insertCartToDb(userCartEntity: UserCartEntity)

    suspend fun deleteUserCartItem(userCartEntity: UserCartEntity)
}
