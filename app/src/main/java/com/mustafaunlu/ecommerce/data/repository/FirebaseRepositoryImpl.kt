package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.common.TokenManager
import com.mustafaunlu.ecommerce.data.source.remote.FirebaseDataSource
import com.mustafaunlu.ecommerce.domain.entity.user.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
    private val tokenManager: TokenManager
) : FirebaseRepository {
    override fun signUpWithFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.signUpWithFirebase(user, onSuccess, onFailure)
    }

    override fun signInWithFirebase(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.signInWithFirebase(
            user,
            onSuccess = { userInformationEntity ->
                tokenManager.saveToken(userInformationEntity.token)
                onSuccess(userInformationEntity)
            },
            onFailure
        )
    }

    override fun forgotPassword(email: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseDataSource.forgotPassword(email, onSuccess, onFailure)
    }

    override fun writeNewUserToFirebaseDatabase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.writeUserDataToFirebase(user, onSuccess, onFailure)
    }

    override fun readUserFromFirebaseDatabase(
        userMail: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.readUserDataFromFirebase(userMail, onSuccess, onFailure)
    }
}