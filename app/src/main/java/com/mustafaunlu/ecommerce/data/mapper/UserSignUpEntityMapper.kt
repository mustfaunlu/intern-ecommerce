package com.mustafaunlu.ecommerce.data.mapper

import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class UserSignUpEntityMapper @Inject constructor() :
    ProductBaseMapper<UserSignUp, SignUpUserEntity> {
    override fun map(input: UserSignUp): SignUpUserEntity {
        return SignUpUserEntity(
            firstName = input.name,
            lastName = input.surname,
            email = input.email,
            password = input.password,
            phone = input.phone,
        )
    }
}
