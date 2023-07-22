package com.mustafaunlu.ecommerce.domain.usecase.user

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import kotlinx.coroutines.flow.Flow

interface UserInfoUseCase {
    operator fun invoke(userId: Int): Flow<NetworkResponseState<UserInformationEntity>>
}