package com.mustafaunlu.ecommerce.domain.usecase.user

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadApiUserInfosUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): ReadApiUserInfosUseCase {
    override fun invoke(userId: String): Flow<NetworkResponseState<UserInformationEntity>> {
        return remoteRepository.getUserInformationByIdFromApi(userId)
    }
}