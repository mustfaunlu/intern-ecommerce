package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in

import com.mustafaunlu.ecommerce.domain.entity.user.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseUserSingInUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): FirebaseUserSingInUseCase {
    override fun invoke(user: FirebaseSignInUserEntity, onSuccess: (UserInformationEntity) -> Unit, onFailure: (String) -> Unit) {
        firebaseRepository.signInWithFirebase(user, onSuccess, onFailure)
    }
}