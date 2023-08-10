package com.mustafaunlu.ecommerce.di.mappers

import com.mustafaunlu.ecommerce.ui.home.ProductUiData
import com.mustafaunlu.ecommerce.ui.favorite.FavoriteUiData
import com.mustafaunlu.ecommerce.ui.detail.DetailProductUiData
import com.mustafaunlu.ecommerce.ui.cart.UserCartUiData
import com.mustafaunlu.ecommerce.ui.profile.UserInformationUiData
import com.mustafaunlu.ecommerce.ui.auth.sign_in.ApiUserUiData
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce.ui.mapper.CartEntityToUiMapper
import com.mustafaunlu.ecommerce.ui.mapper.FavoriteEntityToUiMapper
import com.mustafaunlu.ecommerce.ui.mapper.DetailProductEntityToUiMapper
import com.mustafaunlu.ecommerce.ui.mapper.ProductEntityToUiMapper
import com.mustafaunlu.ecommerce.ui.mapper.CartEntityToFavoriteEntityMapper
import com.mustafaunlu.ecommerce.ui.mapper.CartUiToEntityMapper
import com.mustafaunlu.ecommerce.ui.mapper.FavoriteUiToEntityMapper
import com.mustafaunlu.ecommerce.ui.mapper.UserInfoEntityToUiDataMapper
import com.mustafaunlu.ecommerce.ui.mapper.UserInfoUiDataToEntityMapper
import com.mustafaunlu.ecommerce.ui.mapper.ApiUserUiMapper
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
    abstract fun bindHomeProductUiMapper(productUiDataMapper: ProductEntityToUiMapper): ProductListMapper<ProductEntity, ProductUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindDetailProductUiMapper(productUiDataMapper: DetailProductEntityToUiMapper): ProductBaseMapper<DetailProductEntity, DetailProductUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindCartUiMapper(cartUiDataMapper: CartEntityToUiMapper): ProductListMapper<UserCartEntity, UserCartUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartUiMapper(singleCartUiDataMapper: CartUiToEntityMapper): ProductBaseMapper<UserCartUiData, UserCartEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserUiMapper(userUiDataMapper: ApiUserUiMapper): ProductBaseMapper<UserResponseEntity, ApiUserUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoEntityToUiDataMapper(userInformationUiDataMapper: UserInfoEntityToUiDataMapper): ProductBaseMapper<UserInformationEntity, UserInformationUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoUiDataToEntityMapper(userInformationEntityMapperToUi: UserInfoUiDataToEntityMapper): ProductBaseMapper<UserInformationUiData, UserInformationEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteItemUiMapper(favoriteEntityToUiMapper: FavoriteEntityToUiMapper): ProductListMapper<FavoriteProductEntity, FavoriteUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleFavoriteItemUiMapper(favoriteUiToEntityMapper: FavoriteUiToEntityMapper): ProductBaseMapper<FavoriteUiData, FavoriteProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartToFavoriteEntityMapper(cartEntityToFavoriteEntityMapper: CartEntityToFavoriteEntityMapper): ProductBaseMapper<UserCartEntity, FavoriteProductEntity>
}
