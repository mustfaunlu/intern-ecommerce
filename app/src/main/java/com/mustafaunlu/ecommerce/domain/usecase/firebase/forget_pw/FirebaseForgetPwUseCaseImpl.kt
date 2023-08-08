package com.mustafaunlu.ecommerce.domain.usecase.firebase.forget_pw

import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseForgetPwUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): FirebaseForgetPwUseCase {
    override fun invoke(email: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseRepository.forgotPassword(email, onSuccess, onFailure)
    }
}