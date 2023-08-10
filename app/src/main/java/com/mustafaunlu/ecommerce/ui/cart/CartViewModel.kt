package com.mustafaunlu.ecommerce.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce.domain.usecase.cart.CartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.delete_cart.DeleteUserCartUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.update_cart.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val deleteCartUseCase: DeleteUserCartUseCase,
    private val mapper: ProductListMapper<UserCartEntity, UserCartUiData>,
    private val singleMapper: ProductBaseMapper<UserCartUiData, UserCartEntity>,
    private val badgeUseCase: UserCartBadgeUseCase
) : ViewModel() {
    private val _userCarts = MutableLiveData<ScreenState<List<UserCartUiData>>>()
    val userCarts: LiveData<ScreenState<List<UserCartUiData>>> get() = _userCarts

    private val _totalPriceLiveData: MutableLiveData<Double> = MutableLiveData()
    val totalPriceLiveData: LiveData<Double> get() = _totalPriceLiveData
    fun getCartsByUserId(userId: String) {
        viewModelScope.launch {
            cartUseCase(userId).collect {
                when (it) {
                    is NetworkResponseState.Error -> _userCarts.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _userCarts.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _userCarts.postValue(ScreenState.Success(mapper.map(it.result)))
                }
            }
        }
    }

    fun updateTotalPrice(totalPrice: Double) {
        _totalPriceLiveData.value = totalPrice
    }
    fun deleteUserCartItem(userCartUiData: UserCartUiData) {
        viewModelScope.launch() {
            deleteCartUseCase(singleMapper.map(userCartUiData))
        }
    }
    fun updateUserCartItem(userCartUiData: UserCartUiData) {
        viewModelScope.launch() {
            updateCartUseCase(singleMapper.map(userCartUiData))
        }
    }

    fun setBadgeState(badgeState: UserCartBadgeEntity) {
        viewModelScope.launch {
            badgeUseCase(badgeState)
        }
    }
}
