package com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user

import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity

interface WriteFirebaseUserInfosUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}