package com.mustafaunlu.ecommerce.presentation.sign_up // ktlint-disable package-name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.common.SignUpUserUiData
import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.sign_up.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val mapper: ProductBaseMapper<SignUpUserEntity, SignUpUserUiData>,
) : ViewModel() {
    private val _signUp = MutableLiveData<ScreenState<SignUpUserUiData>>()
    val signUp: LiveData<ScreenState<SignUpUserUiData>> get() = _signUp

    fun signUp(user: UserSignUp) {
        viewModelScope.launch {
            signUpUseCase.invoke(user).collect {
                when (it) {
                    is NetworkResponseState.Error -> _signUp.postValue(ScreenState.Error(it.exception.message.toString()))
                    NetworkResponseState.Loading -> _signUp.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _signUp.postValue(
                        ScreenState.Success(
                            mapper.map(
                                it.result,
                            ),
                        ),
                    )
                }
            }
        }
    }
}
