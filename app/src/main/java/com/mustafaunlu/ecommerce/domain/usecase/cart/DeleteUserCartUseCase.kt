package com.mustafaunlu.ecommerce.domain.usecase.cart

import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity

interface DeleteUserCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
