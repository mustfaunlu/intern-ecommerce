package com.mustafaunlu.ecommerce.di.mappers

import com.mustafaunlu.ecommerce.data.dto.CartResponseProduct
import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.dto.UserInfo
import com.mustafaunlu.ecommerce.data.dto.UserResponse
import com.mustafaunlu.ecommerce.data.mapper.ProductEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.SingleProductEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.UserCartEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.UserInfoEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.UserResponseEntityMapper
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserResponseEntity
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
    abstract fun bindAllProductsEntityMapper(productEntityMapper: ProductEntityMapper): ProductListMapper<Product, ProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleProductEntityMapper(singleProductEntityMapper: SingleProductEntityMapper): ProductBaseMapper<Product, DetailProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserCartEntityMapper(userCartEntityMapper: UserCartEntityMapper): ProductListMapper<CartResponseProduct, UserCartEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserResponseEntityMapper(userResponseEntityMapper: UserResponseEntityMapper): ProductBaseMapper<UserResponse, UserResponseEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoEntityMapper(userInfoEntityMapper: UserInfoEntityMapper): ProductBaseMapper<UserInfo, UserInformationEntity>
}
