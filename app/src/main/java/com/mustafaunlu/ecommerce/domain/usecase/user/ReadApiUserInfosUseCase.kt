package com.mustafaunlu.ecommerce.domain.usecase.user

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import kotlinx.coroutines.flow.Flow

interface ReadApiUserInfosUseCase {
    operator fun invoke(userId: String): Flow<NetworkResponseState<UserInformationEntity>>
}