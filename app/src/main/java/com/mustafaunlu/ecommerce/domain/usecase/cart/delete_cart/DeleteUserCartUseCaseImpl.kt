package com.mustafaunlu.ecommerce.domain.usecase.cart.delete_cart

import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteUserCartUseCaseImpl @Inject constructor(private val repository: LocalRepository) :
    DeleteUserCartUseCase {
    override suspend fun invoke(userCartEntity: UserCartEntity) {
        repository.deleteUserCartItem(userCartEntity)
    }
}
