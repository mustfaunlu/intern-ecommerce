package com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user

import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity

interface FirebaseReadUserUseCase {
    operator fun invoke(
        userMail: String,
        onSuccess: (SignUpUserEntity) -> Unit,
        onFailure: (String) -> Unit
    )
}