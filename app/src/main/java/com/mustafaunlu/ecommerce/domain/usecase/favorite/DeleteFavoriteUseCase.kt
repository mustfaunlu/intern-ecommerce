package com.mustafaunlu.ecommerce.domain.usecase.favorite

import com.mustafaunlu.ecommerce.domain.entity.product.FavoriteProductEntity

interface DeleteFavoriteUseCase {
    suspend operator fun invoke(favoriteProductEntity: FavoriteProductEntity)
}