package com.mustafaunlu.ecommerce.data.source.remote

import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity


interface FirebaseDataSource {
    fun signUpWithFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )

    fun signInWithFirebase(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun forgotPassword(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )

    fun writeUserDataToFirebase(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )

    fun readUserDataFromFirebase(
        userId: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit,
    )
}