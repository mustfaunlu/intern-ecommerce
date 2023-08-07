package com.mustafaunlu.ecommerce.di.mappers

import com.mustafaunlu.ecommerce.presentation.home.AllProductsUiData
import com.mustafaunlu.ecommerce.presentation.favorite.FavoriteUiData
import com.mustafaunlu.ecommerce.presentation.sign_up.SignUpUserUiData
import com.mustafaunlu.ecommerce.presentation.detail.SingleProductUiData
import com.mustafaunlu.ecommerce.presentation.cart.UserCartUiData
import com.mustafaunlu.ecommerce.presentation.profile.UserInformationUiData
import com.mustafaunlu.ecommerce.presentation.login.UserUiData
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce.presentation.mapper.CartUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.FavoriteItemUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.ProductDetailUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.ProductHomeUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.SingleCartToFavoriteEntityMapper
import com.mustafaunlu.ecommerce.presentation.mapper.SingleCartUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.SingleFavoriteItemUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.UserInformationUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.UserSignUpUiMapper
import com.mustafaunlu.ecommerce.presentation.mapper.UserSignUpUiMapperToEntity
import com.mustafaunlu.ecommerce.presentation.mapper.UserUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UiMapperModule {
    @Binds
    @ViewModelScoped
    abstract fun bindHomeProductUiMapper(productUiDataMapper: ProductHomeUiMapper): ProductListMapper<AllProductsEntity, AllProductsUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindDetailProductUiMapper(productUiDataMapper: ProductDetailUiMapper): ProductBaseMapper<SingleProductEntity, SingleProductUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindCartUiMapper(cartUiDataMapper: CartUiMapper): ProductListMapper<UserCartEntity, UserCartUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartUiMapper(singleCartUiDataMapper: SingleCartUiMapper): ProductBaseMapper<UserCartUiData, UserCartEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserUiMapper(userUiDataMapper: UserUiMapper): ProductBaseMapper<UserResponseEntity, UserUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindUserInformationUiMapper(userInformationUiDataMapper: UserInformationUiMapper): ProductBaseMapper<UserInformationEntity, UserInformationUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindUserSignUpUiMapper(userSignUpUiMapper: UserSignUpUiMapper): ProductBaseMapper<SignUpUserEntity, SignUpUserUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindUserSignUpUiMapperToEntity(userSignUpUiMapperToEntity: UserSignUpUiMapperToEntity): ProductBaseMapper<SignUpUserUiData, SignUpUserEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteItemUiMapper(favoriteItemUiMapper: FavoriteItemUiMapper): ProductListMapper<FavoriteItemEntity, FavoriteUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleFavoriteItemUiMapper(singleFavoriteItemUiMapper: SingleFavoriteItemUiMapper): ProductBaseMapper<FavoriteUiData, FavoriteItemEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartToFavoriteEntityMapper(singleCartToFavoriteEntityMapper: SingleCartToFavoriteEntityMapper): ProductBaseMapper<UserCartEntity, FavoriteItemEntity>
}
