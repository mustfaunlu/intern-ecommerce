package com.mustafaunlu.ecommerce.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in.FirebaseSignInUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val mapper: ProductBaseMapper<UserResponseEntity, UserUiData>,
    private val firebaseSignInUseCase: FirebaseSignInUseCase
) : ViewModel() {
    private val _loginState = MutableLiveData<ScreenState<UserUiData>>()
    val loginState: LiveData<ScreenState<UserUiData>> get() = _loginState

    private val _firebaseLoginState = MutableLiveData<ScreenState<FirebaseSignInUserEntity>>()
    val firebaseLoginState: LiveData<ScreenState<FirebaseSignInUserEntity>> get() = _firebaseLoginState

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

    fun loginWithFirebase(user: FirebaseSignInUserEntity) {
        viewModelScope.launch {
            _firebaseLoginState.postValue(ScreenState.Loading)
            firebaseSignInUseCase.invoke(
                user,
                onSuccess = {
                    _firebaseLoginState.postValue(ScreenState.Success(user))
                },
                onFailure = {
                    _firebaseLoginState.postValue(ScreenState.Error(it))
                },
            )
        }
    }
}
