package com.mustafaunlu.ecommerce.domain.usecase.cart // ktlint-disable package-name

import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity

interface UpdateCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
