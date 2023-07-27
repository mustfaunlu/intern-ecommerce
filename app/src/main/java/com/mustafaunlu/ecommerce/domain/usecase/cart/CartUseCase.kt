package com.mustafaunlu.ecommerce.domain.usecase.cart

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartUseCase {
    suspend operator fun invoke(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>> // getCartsByUserIdFromLocal

    suspend operator fun invoke(userCartEntity: UserCartEntity) // insertCartToDb
}
