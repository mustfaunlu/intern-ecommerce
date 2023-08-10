package com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in

import com.mustafaunlu.ecommerce.domain.entity.user.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity

interface FirebaseUserSingInUseCase {
    operator fun invoke(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    )
}