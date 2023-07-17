package com.mustafaunlu.ecommerce.presenter.mapper

import com.mustafaunlu.ecommerce.common.UserUiData
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserUiMapper @Inject constructor() : ProductBaseMapper<UserResponseEntity, UserUiData> {
    override fun map(input: UserResponseEntity): UserUiData {
        return UserUiData(
            id = input.id,
            token = input.token,
            username = input.username,
            image = input.image,
        )
    }
}
