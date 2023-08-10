package com.mustafaunlu.ecommerce.di.usecase

import com.mustafaunlu.ecommerce.domain.usecase.all.GetAllProductsUseCase
import com.mustafaunlu.ecommerce.domain.usecase.all.GetAllProductsUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.cart.CartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.CartUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.badge.UserCartBadgeUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.cart.delete_cart.DeleteUserCartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.delete_cart.DeleteUserCartUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.cart.update_cart.UpdateCartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.update_cart.UpdateCartUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.category.CategoryUseCase
import com.mustafaunlu.ecommerce.domain.usecase.category.CategoryUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.favorite.FavoriteUseCase
import com.mustafaunlu.ecommerce.domain.usecase.favorite.FavoriteUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.favorite.delete_favorite.DeleteFavoriteUseCase
import com.mustafaunlu.ecommerce.domain.usecase.favorite.delete_favorite.DeleteFavoriteUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.forget_pw.ForgotPwFirebaseUserUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.forget_pw.ForgotPwFirebaseUserUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user.ReadFirebaseUserInfosUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user.ReadFirebaseUserInfosUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in.FirebaseUserSingInUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in.FirebaseUserSingInUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up.FirebaseUserSignUpUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up.FirebaseUserSignUpUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user.WriteFirebaseUserInfosCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user.WriteFirebaseUserInfosUseCase
import com.mustafaunlu.ecommerce.domain.usecase.search.SearchUseCase
import com.mustafaunlu.ecommerce.domain.usecase.search.SearchUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.sign_up.SignUpUseCase
import com.mustafaunlu.ecommerce.domain.usecase.sign_up.SignUpUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.single.GetSingleProductUseCase
import com.mustafaunlu.ecommerce.domain.usecase.single.GetSingleProductUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.user.ReadApiUserInfosUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.ReadApiUserInfosUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.user.ApiUserSignInUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.ApiUserSignInUseCaseImpl
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
        userUseCaseImpl: ApiUserSignInUseCaseImpl,
    ): ApiUserSignInUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteUserCartUseCase(
        deleteUserCartUseCaseImpl: DeleteUserCartUseCaseImpl,
    ): DeleteUserCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSearchUseCase(
        searchUseCaseImpl: SearchUseCaseImpl,
    ): SearchUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoUseCase(
        userInfoUseCaseImpl: ReadApiUserInfosUseCaseImpl,
    ): ReadApiUserInfosUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateCartUseCase(
        updateCartUseCaseImpl: UpdateCartUseCaseImpl,
    ): UpdateCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSignUpUseCase(
        signUpUseCaseImpl: SignUpUseCaseImpl,
    ): SignUpUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteUseCase(
        favoriteUseCaseImpl: FavoriteUseCaseImpl,
    ): FavoriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteFavoriteUseCase(
        deleteFavoriteUseCaseImpl: DeleteFavoriteUseCaseImpl,
    ): DeleteFavoriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignUpUseCase(
        firebaseSignUpUseCaseImpl: FirebaseUserSignUpUseCaseImpl,
    ): FirebaseUserSignUpUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignInUseCase(
        firebaseSignInUseCaseImpl: FirebaseUserSingInUseCaseImpl,
    ): FirebaseUserSingInUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseForgetPwUseCase(
        firebaseForgetPwUseCaseImpl: ForgotPwFirebaseUserUseCaseImpl,
    ): ForgotPwFirebaseUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseWriteUserUseCase(
        firebaseWriteUserUseCaseImpl: WriteFirebaseUserInfosCaseImpl,
    ): WriteFirebaseUserInfosUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseReadUserUseCase(
        firebaseReadUserCaseImpl: ReadFirebaseUserInfosUseCaseImpl,
    ): ReadFirebaseUserInfosUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUserCartBadgeUseCase(
        userCartBadgeUseCaseImpl: UserCartBadgeUseCaseImpl,
    ): UserCartBadgeUseCase

}
