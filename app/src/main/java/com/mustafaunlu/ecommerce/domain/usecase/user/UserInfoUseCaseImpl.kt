package com.mustafaunlu.ecommerce.domain.usecase.user

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoUseCaseImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): UserInfoUseCase {
    override fun invoke(userId: Int): Flow<NetworkResponseState<UserInformationEntity>> {
        return remoteRepository.getUserInformationByIdFromApi(userId)
    }
}