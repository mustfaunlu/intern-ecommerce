package com.mustafaunlu.ecommerce.domain.usecase.cart.update_cart // ktlint-disable package-name

import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

interface UpdateCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
