package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.source.local.LocalDataSource
import com.mustafaunlu.ecommerce.di.coroutine.IoDispatcher
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
    override suspend fun getCartsByUserIdFromLocal(userId: Int): Flow<NetworkResponseState<List<UserCartEntity>>> {
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
}
