package com.mustafaunlu.ecommerce.ui.mapper

import com.mustafaunlu.ecommerce.ui.auth.UserInformationUiData
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

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
            token = input.token,
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
            token = input.token,
        )
    }
}
