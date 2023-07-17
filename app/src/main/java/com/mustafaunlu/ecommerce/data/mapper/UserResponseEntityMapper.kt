package com.mustafaunlu.ecommerce.data.mapper

import com.mustafaunlu.ecommerce.data.dto.UserResponse
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserResponseEntityMapper @Inject constructor() :
    ProductBaseMapper<UserResponse, UserResponseEntity> {
    override fun map(input: UserResponse): UserResponseEntity {
        return UserResponseEntity(
            id = input.id,
            token = input.token,
            username = input.username,
            firstName = input.firstName,
            lastName = input.lastName,
            gender = input.gender,
            image = input.image,
            email = input.email,
        )
    }
}
