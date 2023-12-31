package com.mustafaunlu.ecommerce.domain.usecase.user.sign_up

import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity

interface FirebaseUserSignUpUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}