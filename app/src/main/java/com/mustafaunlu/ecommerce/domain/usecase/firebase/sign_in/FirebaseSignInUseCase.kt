package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in

import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity

interface FirebaseSignInUseCase {
    operator fun invoke(
        user: FirebaseSignInUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}