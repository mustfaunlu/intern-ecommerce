package com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user

import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseWriteUserCaseImpl @Inject constructor(
    private val repository: FirebaseRepository
): FirebaseWriteUserUseCase {
    override fun invoke(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.writeNewUserToFirebaseDatabase(user, onSuccess, onFailure)
    }
}