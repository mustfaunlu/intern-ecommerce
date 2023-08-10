package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up

import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseUserSignUpUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : FirebaseUserSignUpUseCase {
    override fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseRepository.signUpWithFirebase(user, onSuccess, onFailure)
    }
}