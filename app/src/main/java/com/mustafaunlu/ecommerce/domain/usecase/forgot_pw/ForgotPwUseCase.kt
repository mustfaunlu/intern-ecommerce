package com.mustafaunlu.ecommerce.domain.usecase.forgot_pw

interface ForgotPwUseCase {
    operator fun invoke(
        email: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit,
    )
}