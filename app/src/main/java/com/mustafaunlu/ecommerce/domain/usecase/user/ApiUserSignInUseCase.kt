package com.mustafaunlu.ecommerce.domain.usecase.user

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.domain.entity.user.UserResponseEntity
import kotlinx.coroutines.flow.Flow

interface ApiUserSignInUseCase {
    operator fun invoke(user: User): Flow<NetworkResponseState<UserResponseEntity>>
}
