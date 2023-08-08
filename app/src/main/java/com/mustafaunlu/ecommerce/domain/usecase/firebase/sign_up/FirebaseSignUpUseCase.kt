package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up

import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity

interface FirebaseSignUpUseCase {
    operator fun invoke(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}