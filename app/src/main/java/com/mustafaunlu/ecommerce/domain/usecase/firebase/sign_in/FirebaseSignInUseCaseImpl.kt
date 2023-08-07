package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in

import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseSignInUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): FirebaseSignInUseCase {
    override fun invoke(user: SignUpUserEntity, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseRepository.signInWithFirebase(user, onSuccess, onFailure)
    }
}