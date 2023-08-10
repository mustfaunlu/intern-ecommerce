package com.mustafaunlu.ecommerce.domain.usecase.firebase.forget_pw

import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class ForgotPwFirebaseUserUseCaseImpl @Inject constructor(
    private val repository: FirebaseRepository,
): ForgotPwFirebaseUserUseCase {
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