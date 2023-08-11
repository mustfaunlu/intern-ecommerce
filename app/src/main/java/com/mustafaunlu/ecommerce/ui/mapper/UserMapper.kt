package com.mustafaunlu.ecommerce.ui.mapper

import com.mustafaunlu.ecommerce.ui.auth.UserInformationUiData
import com.mustafaunlu.ecommerce.ui.auth.sign_in.ApiUserUiData
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class ApiUserUiMapper @Inject constructor() : ProductBaseMapper<UserResponseEntity, ApiUserUiData> {
    override fun map(input: UserResponseEntity): ApiUserUiData {
        return ApiUserUiData(
            id = input.id,
            token = input.token,
            username = input.username,
        )
    }
}

class UserInfoEntityToUiDataMapper @Inject constructor() : ProductBaseMapper<UserInformationEntity, UserInformationUiData> {
    override fun map(input: UserInformationEntity): UserInformationUiData {
        return UserInformationUiData(
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

class UserInfoUiDataToEntityMapper @Inject constructor() : ProductBaseMapper<UserInformationUiData, UserInformationEntity> {
    override fun map(input: UserInformationUiData): UserInformationEntity {
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
