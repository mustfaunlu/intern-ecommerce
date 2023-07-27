package com.mustafaunlu.ecommerce.domain.usecase.sign_up

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : SignUpUseCase {
    override fun invoke(user: UserSignUp): Flow<NetworkResponseState<SignUpUserEntity>> {
        return repository.postSignUpRequest(user)
    }
}
