package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.source.local.LocalDataSource
import com.mustafaunlu.ecommerce.di.coroutine.IoDispatcher
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val localDataSource: LocalDataSource,
) : LocalRepository {
    override suspend fun getCartsByUserIdFromLocal(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>> {
        return flow {
            emit(NetworkResponseState.Success(localDataSource.getUserCartByUserIdFromDb(userId)))
        }.flowOn(ioDispatcher)
    }

    override suspend fun insertCartToDb(userCartEntity: UserCartEntity) {
        withContext(ioDispatcher) {
            localDataSource.insertUserCartToDb(userCartEntity)
        }
    }

    override suspend fun deleteUserCartItem(userCartEntity: UserCartEntity) {
        withContext(ioDispatcher) {
            localDataSource.deleteUserCartFromDb(userCartEntity)
        }
    }

    override suspend fun updateUserCartItem(userCartEntity: UserCartEntity) {
        withContext(ioDispatcher) {
            localDataSource.updateUserCartFromDb(userCartEntity)
        }
    }

    override suspend fun getFavoriteProductsFromLocal(userId: String): Flow<NetworkResponseState<List<FavoriteItemEntity>>> {
        return flow {
            emit(NetworkResponseState.Success(localDataSource.getFavoriteProductsFromDb(userId)))
        }.flowOn(ioDispatcher)
    }

    override suspend fun insertFavoriteItemToDb(favoriteItemEntity: FavoriteItemEntity) {
        withContext(ioDispatcher) {
            localDataSource.insertFavoriteItemToDb(favoriteItemEntity)
        }
    }

    override suspend fun deleteFavoriteItem(favoriteItemEntity: FavoriteItemEntity) {
        withContext(ioDispatcher) {
            localDataSource.deleteFavoriteItemFromDb(favoriteItemEntity)
        }
    }

    override suspend fun getUserCartBadgeStateFromLocal(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>> {
        return flow {
            try {
                emit(NetworkResponseState.Success(localDataSource.getUserCartBadgeStateFromDb(userUniqueInfo)))
            } catch (e: Exception) {
                emit(NetworkResponseState.Success(UserCartBadgeEntity("", false)))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun insertUserCartBadgeStateToDb(userBadge: UserCartBadgeEntity) {
        withContext(ioDispatcher) {
            localDataSource.insertUserCartBadgeCountToDb(userBadge)
        }
    }
}
