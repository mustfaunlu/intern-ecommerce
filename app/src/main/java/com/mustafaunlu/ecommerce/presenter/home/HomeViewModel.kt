package com.mustafaunlu.ecommerce.presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.AllProductsUiData
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce.domain.usecase.all.GetAllProductsUseCase
import com.mustafaunlu.ecommerce.domain.usecase.category.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val mapper: ProductListMapper<AllProductsEntity, AllProductsUiData>,
) :
    ViewModel() {
    private val _products = MutableLiveData<ScreenState<List<AllProductsUiData>>>()
    val products: LiveData<ScreenState<List<AllProductsUiData>>> get() = _products

    private val _categories = MutableLiveData<ScreenState<List<String>>>()
    val categories: LiveData<ScreenState<List<String>>> get() = _categories

    init {
        getAllProducts()
        getAllCategory()
    }
    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase().collectLatest {
                when (it) {
                    is NetworkResponseState.Error -> _products.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _products.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _products.postValue(ScreenState.Success(mapper.map(it.result)))
                }
            }
        }
    }

    private fun getAllCategory() {
        viewModelScope.launch {
            categoryUseCase().collect {
                when (it) {
                    is NetworkResponseState.Error -> _categories.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _categories.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _categories.postValue(ScreenState.Success(it.result))
                }
            }
        }
    }

    fun getProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            categoryUseCase(categoryName).collect {
                when (it) {
                    is NetworkResponseState.Error -> _products.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _products.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _products.postValue(ScreenState.Success(mapper.map(it.result)))
                }
            }
        }
    }
}
