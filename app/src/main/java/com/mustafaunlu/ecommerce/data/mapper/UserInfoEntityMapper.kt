package com.mustafaunlu.ecommerce.data.mapper

import com.mustafaunlu.ecommerce.data.dto.UserInfo
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserInfoEntityMapper @Inject constructor() : ProductBaseMapper<UserInfo, UserInformationEntity> {
    override fun map(input: UserInfo): UserInformationEntity {
        return UserInformationEntity(
            name = input.firstName,
            surname = input.lastName,
            email = input.email,
            phone = input.phone,
            image = input.image,
        )
    }
}
