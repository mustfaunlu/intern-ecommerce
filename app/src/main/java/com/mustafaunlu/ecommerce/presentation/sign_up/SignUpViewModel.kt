package com.mustafaunlu.ecommerce.presentation.sign_up // ktlint-disable package-name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up.FirebaseSignUpUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user.FirebaseWriteUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: FirebaseSignUpUseCase,
    private val firebaseWriteUserUseCase: FirebaseWriteUserUseCase,
    //private val mapper: ProductBaseMapper<SignUpUserEntity, SignUpUserUiData>,
    private val mapper: ProductBaseMapper<SignUpUserUiData, SignUpUserEntity>,
) : ViewModel() {
    private val _signUp = MutableLiveData<ScreenState<SignUpUserUiData>>()
    val signUp: LiveData<ScreenState<SignUpUserUiData>> get() = _signUp

    fun signUp(user: SignUpUserUiData) {
        _signUp.value = ScreenState.Loading
        viewModelScope.launch {
            signUpUseCase.invoke(
                mapper.map(user),
                onSuccess = {
                    _signUp.postValue(ScreenState.Success(user))
                    writeUserToFirebaseDatabase(user)
                },
                onFailure = {
                    _signUp.postValue(ScreenState.Error(it))
                })
        }
    }

    private fun writeUserToFirebaseDatabase(user: SignUpUserUiData) {
        _signUp.value = ScreenState.Loading
        viewModelScope.launch {
            firebaseWriteUserUseCase.invoke(
                mapper.map(user),
                onSuccess = {
                    _signUp.postValue(ScreenState.Success(user))
                },
                onFailure = {
                    _signUp.postValue(ScreenState.Error(it))
                })
        }
    }
}
