package com.mustafaunlu.ecommerce.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce.domain.usecase.product.GetAllProductsUseCase
import com.mustafaunlu.ecommerce.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.mustafaunlu.ecommerce.domain.usecase.category.CategoryUseCase
import com.mustafaunlu.ecommerce.domain.usecase.product.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val searchProductUseCase: SearchProductUseCase,
    private val mapper: ProductListMapper<ProductEntity, ProductUiData>,
    private val badgeUseCase: UserCartBadgeUseCase
) :
    ViewModel() {
    private val _products = MutableLiveData<ScreenState<List<ProductUiData>>>()
    val products: LiveData<ScreenState<List<ProductUiData>>> get() = _products

    private val _categories = MutableLiveData<ScreenState<List<String>>>()
    val categories: LiveData<ScreenState<List<String>>> get() = _categories

    private val _badge = MutableLiveData<ScreenState<UserCartBadgeEntity>>()
    val badge: LiveData<ScreenState<UserCartBadgeEntity>> get() = _badge

    init {
        getAllCategory()
        getAllProducts()
    }

    fun getBadgeState (userUniqueInfo: String) {
        viewModelScope.launch {
            badgeUseCase(userUniqueInfo).onEach {
                when (it) {
                    is NetworkResponseState.Error -> _badge.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _badge.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _badge.postValue(ScreenState.Success(it.result))
                }
            }.launchIn(viewModelScope)
        }
    }
    private fun getAllProducts() {
        getAllProductsUseCase().onEach {
            when (it) {
                is NetworkResponseState.Error -> _products.postValue(ScreenState.Error(it.exception.message!!))
                is NetworkResponseState.Loading -> _products.postValue(ScreenState.Loading)
                is NetworkResponseState.Success -> _products.postValue(ScreenState.Success(mapper.map(it.result)))
            }
        }.launchIn(viewModelScope)
    }

    fun searchProduct(query: String) {
        viewModelScope.launch {
            searchProductUseCase(query).onEach {
                when (it) {
                    is NetworkResponseState.Error -> _products.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _products.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _products.postValue(ScreenState.Success(mapper.map(it.result)))
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getAllCategory() {
        categoryUseCase().onEach {
            when (it) {
                is NetworkResponseState.Error -> _categories.postValue(ScreenState.Error(it.exception.message!!))
                is NetworkResponseState.Loading -> _categories.postValue(ScreenState.Loading)
                is NetworkResponseState.Success -> _categories.postValue(ScreenState.Success(it.result))
            }
        }.launchIn(viewModelScope)
    }

    fun getProductsByCategory(categoryName: String) {
        getAllProductsUseCase(categoryName).onEach {
            when (it) {
                is NetworkResponseState.Error -> _products.postValue(ScreenState.Error(it.exception.message!!))
                is NetworkResponseState.Loading -> _products.postValue(ScreenState.Loading)
                is NetworkResponseState.Success -> _products.postValue(ScreenState.Success(mapper.map(it.result)))
            }
        }.launchIn(viewModelScope)
    }
}
