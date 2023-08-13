package com.mustafaunlu.ecommerce.di.mappers

import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.data.mapper.ProductEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.SingleProductEntityMapper
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
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
}
