package com.mustafaunlu.ecommerce.domain.usecase.forgot_pw

import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class ForgotPwUseCaseImpl @Inject constructor(
    private val repository: FirebaseRepository,
): ForgotPwUseCase {
    override fun invoke(email: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        repository.forgotPassword(
            email,
            onSuccess = {
                onSuccess("Success")
            },
            onFailure = {
                onFailure(it)
            },
        )
    }

}