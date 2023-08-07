package com.mustafaunlu.ecommerce.presentation.mapper

import com.mustafaunlu.ecommerce.presentation.sign_up.SignUpUserUiData
import com.mustafaunlu.ecommerce.presentation.profile.UserInformationUiData
import com.mustafaunlu.ecommerce.presentation.login.UserUiData
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserUiMapper @Inject constructor() : ProductBaseMapper<UserResponseEntity, UserUiData> {
    override fun map(input: UserResponseEntity): UserUiData {
        return UserUiData(
            id = input.id,
            token = input.token,
            username = input.username,
        )
    }
}

class UserInformationUiMapper @Inject constructor() : ProductBaseMapper<UserInformationEntity, UserInformationUiData> {
    override fun map(input: UserInformationEntity): UserInformationUiData {
        return UserInformationUiData(
            name = input.name,
            surname = input.surname,
            email = input.email,
            phone = input.phone,
            image = input.image,
        )
    }
}

class SignUpUserEntityUiMapper @Inject constructor() : ProductBaseMapper<SignUpUserEntity, UserInformationUiData> {
    override fun map(input: SignUpUserEntity): UserInformationUiData {
        return UserInformationUiData(
            name = input.firstName,
            surname = input.lastName,
            email = input.email,
            phone = input.phone,
            image = "",
        )
    }
}
class UserSignUpUiMapper @Inject constructor() : ProductBaseMapper<SignUpUserEntity, SignUpUserUiData> {
    override fun map(input: SignUpUserEntity): SignUpUserUiData {
        return SignUpUserUiData(
            name = input.firstName,
            surname = input.lastName,
            email = input.email,
            phone = input.phone,
            password = input.password,
        )
    }
}

class UserSignUpUiMapperToEntity @Inject constructor() : ProductBaseMapper<SignUpUserUiData, SignUpUserEntity> {
    override fun map(input: SignUpUserUiData): SignUpUserEntity {
        return SignUpUserEntity(
            firstName = input.name,
            lastName = input.surname,
            email = input.email,
            phone = input.phone,
            password = input.password,
        )
    }
}
