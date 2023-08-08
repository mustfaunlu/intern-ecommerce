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
import com.mustafaunlu.ecommerce.domain.usecase.firebase.forget_pw.FirebaseForgetPwUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.forget_pw.FirebaseForgetPwUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user.FirebaseReadUserUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user.FirebaseReadUserUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in.FirebaseSignInUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in.FirebaseSignInUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up.FirebaseSignUpUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up.FirebaseSignUpUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user.FirebaseWriteUserCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user.FirebaseWriteUserUseCase
import com.mustafaunlu.ecommerce.domain.usecase.search.SearchUseCase
import com.mustafaunlu.ecommerce.domain.usecase.search.SearchUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.sign_up.SignUpUseCase
import com.mustafaunlu.ecommerce.domain.usecase.sign_up.SignUpUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.single.GetSingleProductUseCase
import com.mustafaunlu.ecommerce.domain.usecase.single.GetSingleProductUseCaseImpl
import com.mustafaunlu.ecommerce.domain.usecase.user.UserInfoUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.UserInfoUseCaseImpl
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

    @Binds
    @ViewModelScoped
    abstract fun bindSearchUseCase(
        searchUseCaseImpl: SearchUseCaseImpl,
    ): SearchUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoUseCase(
        userInfoUseCaseImpl: UserInfoUseCaseImpl,
    ): UserInfoUseCase

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
        firebaseSignUpUseCaseImpl: FirebaseSignUpUseCaseImpl,
    ): FirebaseSignUpUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignInUseCase(
        firebaseSignInUseCaseImpl: FirebaseSignInUseCaseImpl,
    ): FirebaseSignInUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseForgetPwUseCase(
        firebaseForgetPwUseCaseImpl: FirebaseForgetPwUseCaseImpl,
    ): FirebaseForgetPwUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseWriteUserUseCase(
        firebaseWriteUserUseCaseImpl: FirebaseWriteUserCaseImpl,
    ): FirebaseWriteUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseReadUserUseCase(
        firebaseReadUserCaseImpl: FirebaseReadUserUseCaseImpl,
    ): FirebaseReadUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUserCartBadgeUseCase(
        userCartBadgeUseCaseImpl: UserCartBadgeUseCaseImpl,
    ): UserCartBadgeUseCase
}
