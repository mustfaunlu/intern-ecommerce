package com.mustafaunlu.ecommerce.presentation.mapper

import com.mustafaunlu.ecommerce.common.FavoriteUiData
import com.mustafaunlu.ecommerce.common.UserCartUiData
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import javax.inject.Inject

class CartUiMapper @Inject constructor() : ProductListMapper<UserCartEntity, UserCartUiData> {
    override fun map(input: List<UserCartEntity>): List<UserCartUiData> {
        return input.map {
            UserCartUiData(
                userId = it.userId,
                productId = it.productId,
                price = it.price,
                quantity = it.quantity,
                title = it.title,
                imageUrl = it.image,
            )
        }
    }
}

class SingleCartUiMapper @Inject constructor() : ProductBaseMapper<UserCartUiData, UserCartEntity> {
    override fun map(input: UserCartUiData): UserCartEntity {
        return UserCartEntity(
            userId = input.userId,
            productId = input.productId,
            price = input.price,
            quantity = input.quantity,
            title = input.title,
            image = input.imageUrl,
        )
    }
}
class SingleCartToFavoriteEntityMapper @Inject constructor() : ProductBaseMapper<UserCartEntity, FavoriteItemEntity> {
    override fun map(input: UserCartEntity): FavoriteItemEntity {
        return FavoriteItemEntity(
            userId = input.userId,
            productId = input.productId,
            price = input.price,
            quantity = input.quantity,
            title = input.title,
            image = input.image,
        )
    }
}
