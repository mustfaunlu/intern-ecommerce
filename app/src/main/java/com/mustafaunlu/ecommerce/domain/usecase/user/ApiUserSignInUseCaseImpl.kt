package com.mustafaunlu.ecommerce.domain.usecase.user

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiUserSignInUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : ApiUserSignInUseCase {
    override fun invoke(user: User): Flow<NetworkResponseState<UserResponseEntity>> {
        return repository.postLoginRequest(user)
    }
}
