package com.mustafaunlu.ecommerce.domain.usecase.favorite.delete_favorite

import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteFavoriteUseCaseImpl @Inject constructor(
    private val repository: LocalRepository,
) : DeleteFavoriteUseCase {
    override suspend fun invoke(favoriteItemEntity: FavoriteItemEntity) {
        repository.deleteFavoriteItem(favoriteItemEntity)
    }
}
