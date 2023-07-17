package com.mustafaunlu.ecommerce.domain.usecase.cart

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import com.mustafaunlu.ecommerce.domain.usecase.cart.CartUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(private val repository: LocalRepository) : CartUseCase {
    override fun invoke(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>> = repository.getCartsByUserIdFromLocal(userId)
    override suspend fun invoke(userCartEntity: UserCartEntity) {
        repository.insertCartToDb(userCartEntity)
    }
}