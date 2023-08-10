package com.mustafaunlu.ecommerce.presentation.auth.sign_up // ktlint-disable package-name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.firebase.sign_up.FirebaseUserSignUpUseCase
import com.mustafaunlu.ecommerce.domain.usecase.firebase.write_user.WriteFirebaseUserInfosUseCase
import com.mustafaunlu.ecommerce.presentation.profile.FirebaseUserUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: FirebaseUserSignUpUseCase,
    private val writeFirebaseUserInfosUseCase: WriteFirebaseUserInfosUseCase,
    private val userInfoToEntity: ProductBaseMapper<FirebaseUserUiData, UserInformationEntity>,
) : ViewModel() {
    private val _signUp = MutableLiveData<ScreenState<FirebaseUserUiData>>()
    val signUp: LiveData<ScreenState<FirebaseUserUiData>> get() = _signUp

    fun signUp(user: FirebaseUserUiData) {
        _signUp.value = ScreenState.Loading
        viewModelScope.launch {
            signUpUseCase.invoke(
                userInfoToEntity.map(user),
                onSuccess = {
                    _signUp.postValue(ScreenState.Success(user))
                    writeUserToFirebaseDatabase(userInfoToEntity.map(user))
                }
            ) {
                _signUp.postValue(ScreenState.Error(it))
            }
        }
    }

    private fun writeUserToFirebaseDatabase(user: UserInformationEntity) {
        viewModelScope.launch {
            writeFirebaseUserInfosUseCase.invoke(
                user,
                onSuccess = {}
            ) {
                _signUp.postValue(ScreenState.Error(it))
            }
        }
    }
}
