package com.mustafaunlu.ecommerce.presentation.mapper

import com.mustafaunlu.ecommerce.presentation.profile.FirebaseUserUiData
import com.mustafaunlu.ecommerce.presentation.auth.sign_in.ApiUserUiData
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserUiMapper @Inject constructor() : ProductBaseMapper<UserResponseEntity, ApiUserUiData> {
    override fun map(input: UserResponseEntity): ApiUserUiData {
        return ApiUserUiData(
            id = input.id,
            token = input.token,
            username = input.username,
        )
    }
}

class UserInfoEntityToUiDataMapper @Inject constructor() : ProductBaseMapper<UserInformationEntity, FirebaseUserUiData> {
    override fun map(input: UserInformationEntity): FirebaseUserUiData {
        return FirebaseUserUiData(
            id = input.id,
            name = input.name,
            surname = input.surname,
            email = input.email,
            phone = input.phone,
            image = input.image,
            password = input.password,
        )
    }
}

class UserInfoUiDataToEntityMapper @Inject constructor() : ProductBaseMapper<FirebaseUserUiData, UserInformationEntity> {
    override fun map(input: FirebaseUserUiData): UserInformationEntity {
        return UserInformationEntity(
            id = input.id,
            name = input.name,
            surname = input.surname,
            email = input.email,
            phone = input.phone,
            image = input.image,
            password = input.password,
        )
    }
}
