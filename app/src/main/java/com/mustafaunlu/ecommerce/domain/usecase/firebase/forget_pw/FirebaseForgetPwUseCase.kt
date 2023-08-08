package com.mustafaunlu.ecommerce.domain.usecase.firebase.forget_pw

interface FirebaseForgetPwUseCase {
    operator fun invoke(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}