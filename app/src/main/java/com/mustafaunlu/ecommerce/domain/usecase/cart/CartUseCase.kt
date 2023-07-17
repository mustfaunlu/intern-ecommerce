package com.mustafaunlu.ecommerce.domain.usecase.cart

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartUseCase {
    operator fun invoke(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>>

    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
