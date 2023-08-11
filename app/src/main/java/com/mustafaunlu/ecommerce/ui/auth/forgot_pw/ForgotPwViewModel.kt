package com.mustafaunlu.ecommerce.ui.auth.forgot_pw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.usecase.user.forget_pw.ForgotPwFirebaseUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPwViewModel @Inject constructor(
    private val useCase: ForgotPwFirebaseUserUseCase,
): ViewModel() {
    private val _forgotPassword = MutableLiveData<ScreenState<String>>()
    val forgotPassword: LiveData<ScreenState<String>> get() = _forgotPassword

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPassword.postValue(ScreenState.Loading)
            useCase.invoke(
                email,
                onSuccess = {
                    _forgotPassword.postValue(ScreenState.Success(it))
                },
                onFailure = {
                    _forgotPassword.postValue(ScreenState.Error(it))
                },
            )
        }
    }
}