package com.mustafaunlu.ecommerce.data.repository

import com.mustafaunlu.ecommerce.data.source.remote.FirebaseDataSource
import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
) : FirebaseRepository {
    override fun signUpWithFirebase(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.signUpWithFirebase(user, onSuccess, onFailure)
    }

    override fun signInWithFirebase(
        user: FirebaseSignInUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.signInWithFirebase(user, onSuccess, onFailure)
    }

    override fun forgotPassword(email: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseDataSource.forgotPassword(email, onSuccess, onFailure)
    }

    override fun writeNewUserToFirebaseDatabase(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.writeUserDataToFirebase(user, onSuccess, onFailure)
    }

    override fun readUserFromFirebaseDatabase(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.readUserDataFromFirebase(user, onSuccess, onFailure)
    }
}