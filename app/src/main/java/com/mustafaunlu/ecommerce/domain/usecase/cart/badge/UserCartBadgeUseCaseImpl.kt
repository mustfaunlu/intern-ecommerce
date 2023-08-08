package com.mustafaunlu.ecommerce.domain.usecase.cart.badge

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserCartBadgeUseCaseImpl @Inject constructor(
    private val repository: LocalRepository
) : UserCartBadgeUseCase {
    override suspend fun invoke(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>> {
        return repository.getUserCartBadgeStateFromLocal(userUniqueInfo)
    }

    override suspend fun invoke(userCartBadgeEntity: UserCartBadgeEntity) {
        repository.insertUserCartBadgeStateToDb(userCartBadgeEntity)
    }
}