package com.mustafaunlu.ecommerce.domain.usecase.cart // ktlint-disable package-name

import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import javax.inject.Inject

class UpdateCartUseCaseImpl @Inject constructor(
    private val repository: LocalRepository,
) : UpdateCartUseCase {
    override suspend fun invoke(userCartEntity: UserCartEntity) {
        repository.updateUserCart(userCartEntity)
    }
}
