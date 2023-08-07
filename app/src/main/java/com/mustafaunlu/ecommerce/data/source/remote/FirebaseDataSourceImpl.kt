package com.mustafaunlu.ecommerce.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
   private val firebaseAuth: FirebaseAuth
): FirebaseDataSource {
    override fun signUpWithFirebase(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }
    }

    override fun signInWithFirebase(
        user: FirebaseSignInUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }
    }

    override fun forgotPassword(email: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }
    }
}