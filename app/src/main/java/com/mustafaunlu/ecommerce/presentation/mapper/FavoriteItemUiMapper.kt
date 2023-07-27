package com.mustafaunlu.ecommerce.presentation.mapper

import com.mustafaunlu.ecommerce.common.FavoriteUiData
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import javax.inject.Inject

class FavoriteItemUiMapper @Inject constructor() : ProductListMapper<FavoriteItemEntity, FavoriteUiData> {
    override fun map(input: List<FavoriteItemEntity>): List<FavoriteUiData> {
        return input.map {
            FavoriteUiData(
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

class SingleFavoriteItemUiMapper @Inject constructor() : ProductBaseMapper<FavoriteUiData, FavoriteItemEntity> {
    override fun map(input: FavoriteUiData): FavoriteItemEntity {
        return FavoriteItemEntity(
            userId = input.userId,
            productId = input.productId,
            price = input.price,
            quantity = input.quantity,
            title = input.title,
            image = input.imageUrl,
        )
    }
}