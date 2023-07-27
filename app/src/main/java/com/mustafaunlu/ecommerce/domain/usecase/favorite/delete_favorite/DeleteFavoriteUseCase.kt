package com.mustafaunlu.ecommerce.domain.usecase.favorite.delete_favorite

import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity

interface DeleteFavoriteUseCase {
    suspend operator fun invoke(favoriteItemEntity: FavoriteItemEntity)
}