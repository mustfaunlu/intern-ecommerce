package com.mustafaunlu.ecommerce.domain.usecase.sign_up

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {
    operator fun invoke(user: UserSignUp): Flow<NetworkResponseState<SignUpUserEntity>>
}
