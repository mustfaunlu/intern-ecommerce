package com.mustafaunlu.ecommerce.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.presentation.cart.UserCartUiData
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce.domain.usecase.cart.CartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.favorite.FavoriteUseCase
import com.mustafaunlu.ecommerce.domain.usecase.single.GetSingleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val cartUseCase: CartUseCase,
    private val cartmapper: ProductListMapper<UserCartEntity, UserCartUiData>,
    private val mapper: ProductBaseMapper<SingleProductEntity, SingleProductUiData>,
    private val favoriteUseCase: FavoriteUseCase,
    private val cartToFavoriteUiMapper: ProductBaseMapper<UserCartEntity, FavoriteItemEntity>,
) : ViewModel() {
    private val _product = MutableLiveData<ScreenState<SingleProductUiData>>()
    val product: LiveData<ScreenState<SingleProductUiData>> get() = _product

    fun getProduct(id: Int) {
        viewModelScope.launch {
            getSingleProductUseCase(id).collect {
                when (it) {
                    is NetworkResponseState.Error -> _product.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _product.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _product.postValue(ScreenState.Success(mapper.map(it.result)))
                }
            }
        }
    }

    fun addToCart(userCartEntity: UserCartEntity) {
        viewModelScope.launch {
            cartUseCase(userCartEntity)
        }
    }

    fun addToFavorite(userCartUiData: UserCartEntity) {
        viewModelScope.launch {
            favoriteUseCase(cartToFavoriteUiMapper.map(userCartUiData))
        }
    }
}
