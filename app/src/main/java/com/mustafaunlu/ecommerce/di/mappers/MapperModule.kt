package com.mustafaunlu.ecommerce.di.mappers

import com.mustafaunlu.ecommerce.data.dto.CartResponseProduct
import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.UserInfo
import com.mustafaunlu.ecommerce.data.dto.UserResponse
import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import com.mustafaunlu.ecommerce.data.mapper.AllProductsEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.SingleProductEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.UserCartEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.UserInfoEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.UserResponseEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.UserSignUpEntityMapper
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAllProductsEntityMapper(allProductsEntityMapper: AllProductsEntityMapper): ProductListMapper<Product, AllProductsEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleProductEntityMapper(singleProductEntityMapper: SingleProductEntityMapper): ProductBaseMapper<Product, SingleProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserCartEntityMapper(userCartEntityMapper: UserCartEntityMapper): ProductListMapper<CartResponseProduct, UserCartEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserResponseEntityMapper(userResponseEntityMapper: UserResponseEntityMapper): ProductBaseMapper<UserResponse, UserResponseEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoEntityMapper(userInfoEntityMapper: UserInfoEntityMapper): ProductBaseMapper<UserInfo, UserInformationEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserSignUpEntityMapper(userSignUpEntityMapper: UserSignUpEntityMapper): ProductBaseMapper<UserSignUp, SignUpUserEntity>
}
