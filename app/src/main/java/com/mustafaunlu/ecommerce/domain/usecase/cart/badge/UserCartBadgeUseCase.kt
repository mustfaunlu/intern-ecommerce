package com.mustafaunlu.ecommerce.domain.usecase.cart.badge

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import kotlinx.coroutines.flow.Flow

interface UserCartBadgeUseCase {
    suspend operator fun invoke(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>>

    suspend operator fun invoke(userCartBadgeEntity: UserCartBadgeEntity)
}