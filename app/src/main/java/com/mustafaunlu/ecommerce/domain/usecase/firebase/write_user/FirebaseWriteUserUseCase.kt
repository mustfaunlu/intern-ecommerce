package com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user

import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity

interface FirebaseWriteUserUseCase {
    operator fun invoke(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}