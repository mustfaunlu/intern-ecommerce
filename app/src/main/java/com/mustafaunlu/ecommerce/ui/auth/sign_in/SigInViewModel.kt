package com.mustafaunlu.ecommerce.ui.auth.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.domain.entity.user.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.entity.user.UserResponseEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_in.FirebaseUserSingInUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.ApiUserSignInUseCase
import com.mustafaunlu.ecommerce.ui.profile.UserInformationUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(
    private val apiUserSignInUseCase: ApiUserSignInUseCase,
    private val apiUserInfoToUiData: ProductBaseMapper<UserResponseEntity, ApiUserUiData>,
    private val firebaseUserSingInUseCase: FirebaseUserSingInUseCase,
    private val firebaseUserInfoToUiData: ProductBaseMapper<UserInformationEntity, UserInformationUiData>,
) : ViewModel() {
    private val _apiLoginState = MutableLiveData<ScreenState<ApiUserUiData>>()
    val apiLoginState: LiveData<ScreenState<ApiUserUiData>> get() = _apiLoginState

    private val _firebaseLoginState = MutableLiveData<ScreenState<UserInformationUiData>>()
    val firebaseLoginState: LiveData<ScreenState<UserInformationUiData>> get() = _firebaseLoginState

    fun login(user: User) {
        viewModelScope.launch {
            apiUserSignInUseCase(user).collect {
                when (it) {
                    is NetworkResponseState.Error -> {
                        _apiLoginState.postValue(ScreenState.Error(it.exception.message.toString()))
                    }
                    NetworkResponseState.Loading -> _apiLoginState.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _apiLoginState.postValue(
                        ScreenState.Success(
                            apiUserInfoToUiData.map(it.result),
                        ),
                    )
                }
            }
        }
    }

    fun loginWithFirebase(user: FirebaseSignInUserEntity) {
        viewModelScope.launch {
            _firebaseLoginState.postValue(ScreenState.Loading)
            firebaseUserSingInUseCase.invoke(
                user,
                onSuccess = {
                    _firebaseLoginState.postValue(
                        ScreenState.Success(
                            firebaseUserInfoToUiData.map(
                                it
                            )
                        )
                    )
                },
            ) {
                _firebaseLoginState.postValue(ScreenState.Error(it))
            }
        }
    }
}
