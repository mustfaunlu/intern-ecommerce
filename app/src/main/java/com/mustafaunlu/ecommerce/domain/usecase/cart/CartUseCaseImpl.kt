package com.mustafaunlu.ecommerce.domain.usecase.cart

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(private val repository: LocalRepository) : CartUseCase {
    override suspend fun invoke(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>> = repository.getCartsByUserIdFromDb(userId)
    override suspend fun invoke(userCartEntity: UserCartEntity) {
        repository.insertUserCartToDb(userCartEntity)
    }
}
