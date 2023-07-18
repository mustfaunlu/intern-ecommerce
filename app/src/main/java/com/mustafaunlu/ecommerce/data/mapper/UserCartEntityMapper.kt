package com.mustafaunlu.ecommerce.data.mapper

import com.mustafaunlu.ecommerce.data.dto.CartResponseProduct
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import javax.inject.Inject

class UserCartEntityMapper @Inject constructor() :
    ProductListMapper<CartResponseProduct, UserCartEntity> {
    override fun map(input: List<CartResponseProduct>): List<UserCartEntity> {
        return input.map {
            UserCartEntity(
                userId = 0,
                productId = it.id,
                price = it.price,
                quantity = it.quantity,
                title = it.title,
                image = "",
            )
        }
    }
}