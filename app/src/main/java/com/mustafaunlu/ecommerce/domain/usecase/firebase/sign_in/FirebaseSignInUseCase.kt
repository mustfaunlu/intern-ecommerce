package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in

import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity

interface FirebaseSignInUseCase {
    operator fun invoke(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}