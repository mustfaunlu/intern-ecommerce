package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up

import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseSignUpUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : FirebaseSignUpUseCase {
    override fun invoke(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseRepository.signUpWithFirebase(user, onSuccess, onFailure)
    }
}