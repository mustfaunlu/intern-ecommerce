package com.mustafaunlu.ecommerce.domain.usecase.cart.delete_cart

import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

interface DeleteUserCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
