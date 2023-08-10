package com.mustafaunlu.ecommerce.ui.mapper

import com.mustafaunlu.ecommerce.ui.favorite.FavoriteUiData
import com.mustafaunlu.ecommerce.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import javax.inject.Inject

class FavoriteEntityToUiMapper @Inject constructor() : ProductListMapper<FavoriteProductEntity, FavoriteUiData> {
    override fun map(input: List<FavoriteProductEntity>): List<FavoriteUiData> {
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

class FavoriteUiToEntityMapper @Inject constructor() : ProductBaseMapper<FavoriteUiData, FavoriteProductEntity> {
    override fun map(input: FavoriteUiData): FavoriteProductEntity {
        return FavoriteProductEntity(
            userId = input.userId,
            productId = input.productId,
            price = input.price,
            quantity = input.quantity,
            title = input.title,
            image = input.imageUrl,
        )
    }
}