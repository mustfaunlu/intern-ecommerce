package com.mustafaunlu.ecommerce.domain.repository

import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity

interface FirebaseRepository {
    fun signUpWithFirebase(
        user: SignUpUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun signInWithFirebase(
        user: FirebaseSignInUserEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun forgotPassword(email: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}