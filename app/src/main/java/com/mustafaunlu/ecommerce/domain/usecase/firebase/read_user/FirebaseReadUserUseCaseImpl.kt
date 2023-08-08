package com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user

import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseReadUserUseCaseImpl @Inject constructor(
    private val repository: FirebaseRepository
): FirebaseReadUserUseCase {
    override fun invoke(
        userMail: String,
        onSuccess: (SignUpUserEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.readUserFromFirebaseDatabase(userMail, onSuccess, onFailure)
    }
}