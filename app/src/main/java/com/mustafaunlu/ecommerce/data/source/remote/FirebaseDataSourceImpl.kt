package com.mustafaunlu.ecommerce.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FirebaseDataSource {
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

    override fun writeUserDataToFirebase(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val userMap = hashMapOf(
            "id" to firebaseAuth.uid,
            "name" to user.firstName,
            "surname" to user.lastName,
            "email" to user.email,
            "phone" to user.phone,
        )
        firestore.collection("users").document(user.email).set(userMap)
            .addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }
    }

    override fun readUserDataFromFirebase(
        userMail: String,
        onSuccess: (SignUpUserEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firestore.collection("users").document(userMail).get()
            .addOnSuccessListener { snapshot ->
                onSuccess(
                    SignUpUserEntity(
                        firstName = snapshot.getString("name") ?: "",
                        lastName = snapshot.getString("surname") ?: "",
                        email = snapshot.getString("email") ?: "",
                        phone = snapshot.getString("phone") ?: "",
                        password = "",
                    )
                )
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }
    }
}