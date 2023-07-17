package com.mustafaunlu.ecommerce.di.usecase

import com.mustafaunlu.ecommerce.domain.usecase.all.GetAllProductsUseCase
import com.mustafaunlu.ecommerce.domain.usecase.all.GetAllProductsUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.cart.CartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.CartUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.cart.delete_cart.DeleteUserCartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.delete_cart.DeleteUserCartUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.category.CategoryUseCase
import com.mustafaunlu.ecommerce.domain.usecase.category.CategoryUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.single.GetSingleProductUseCase
import com.mustafaunlu.ecommerce.domain.usecase.single.GetSingleProductUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.user.UserUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllProductsUseCase(
        getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl,
    ): GetAllProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSingleProductUseCase(
        getSingleProductUseCaseImpl: GetSingleProductUseCaseImpl,
    ): GetSingleProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllCategoryUseCase(
        getAllCategoryUseCaseImpl: CategoryUseCaseImpl,
    ): CategoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindCartUseCase(
        cartUseCaseImpl: CartUseCaseImpl,
    ): CartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUserUseCase(
        userUseCaseImpl: UserUseCaseImpl,
    ): UserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteUserCartUseCase(
        deleteUserCartUseCaseImpl: DeleteUserCartUseCaseImpl,
    ): DeleteUserCartUseCase
}
