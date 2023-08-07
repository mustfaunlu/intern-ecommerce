package com.mustafaunlu.ecommerce.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val mapper: ProductBaseMapper<UserResponseEntity, UserUiData>,
) : ViewModel() {
    private val _loginState = MutableLiveData<ScreenState<UserUiData>>()
    val loginState: LiveData<ScreenState<UserUiData>> get() = _loginState

    fun login(user: User) {
        viewModelScope.launch {
            userUseCase(user).collect {
                when (it) {
                    is NetworkResponseState.Error -> _loginState.postValue(ScreenState.Error(it.exception.message.toString()))
                    NetworkResponseState.Loading -> _loginState.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _loginState.postValue(
                        ScreenState.Success(
                            mapper.map(it.result),
                        ),
                    )
                }
            }
        }
    }
}
