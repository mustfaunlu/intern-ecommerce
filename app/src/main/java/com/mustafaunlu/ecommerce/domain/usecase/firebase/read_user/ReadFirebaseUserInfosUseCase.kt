package com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user

import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity

interface ReadFirebaseUserInfosUseCase {
    operator fun invoke(
        userMail: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    )
}