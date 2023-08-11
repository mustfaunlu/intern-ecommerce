package com.mustafaunlu.ecommerce.domain.usecase.user.read_user

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import kotlinx.coroutines.flow.Flow

interface ReadApiUserInfosUseCase {
    operator fun invoke(userId: String): Flow<NetworkResponseState<UserInformationEntity>>
}