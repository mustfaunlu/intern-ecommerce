package com.mustafaunlu.ecommerce.data.source.remote

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.mustafaunlu.ecommerce.domain.entity.user.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    firestore: FirebaseFirestore
) : FirebaseDataSource {

    private val collection = firestore.collection("users")
    override fun signUpWithFirebase(
        user: UserInformationEntity,
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
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                val firebaseUser = it.user
                onSuccess(
                    UserInformationEntity(
                        id = firebaseUser?.uid ?: "",
                        name = firebaseUser?.displayName ?: "",
                        surname = "",
                        email = firebaseUser?.email ?: "",
                        phone = firebaseUser?.phoneNumber ?: "",
                        image = firebaseUser?.photoUrl.toString(),
                        password = "",
                    )
                )
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
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val userMap = hashMapOf(
            "id" to firebaseAuth.uid,
            "name" to user.name,
            "surname" to user.surname,
            "email" to user.email,
            "phone" to user.phone,
            "image" to user.image,
        )
        val firebaseUser = firebaseAuth.currentUser
        val profileUpdate = userProfileChangeRequest {
            displayName = userMap["name"]
            photoUri = Uri.parse(userMap["image"])
        }
        firebaseUser?.updateProfile(profileUpdate)
        collection.document(firebaseAuth.uid!!).set(userMap)
            .addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }
    }

    override fun readUserDataFromFirebase(
        userId: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        collection.document(userId).get()
            .addOnSuccessListener { snapshot ->
                onSuccess(
                    UserInformationEntity(
                        id = snapshot.getString("id") ?: "",
                        name = snapshot.getString("name") ?: "",
                        surname = snapshot.getString("surname") ?: "",
                        email = snapshot.getString("email") ?: "",
                        phone = snapshot.getString("phone") ?: "",
                        image = snapshot.getString("image") ?: "",
                        password = "",
                    )
                )
            }.addOnFailureListener {
                onFailure(it.message ?: "An error occurred")
            }
    }
}