package com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user

import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class ReadFirebaseUserInfosUseCaseImpl @Inject constructor(
    private val repository: FirebaseRepository
): ReadFirebaseUserInfosUseCase {
    override fun invoke(
        userMail: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.readUserFromFirebaseDatabase(userMail, onSuccess, onFailure)
    }
}