package com.mustafaunlu.ecommerce.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.entity.SignUpUserEntity
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user.FirebaseReadUserUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.UserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userInformationUseCase: UserInfoUseCase,
    private val firebaseReadUserUseCase: FirebaseReadUserUseCase,
    private val userInformationMapper: ProductBaseMapper<UserInformationEntity, UserInformationUiData>,
    private val signUpUserEntityMapper: ProductBaseMapper<SignUpUserEntity, UserInformationUiData>,
) : ViewModel() {
    private val _userInformation = MutableLiveData<ScreenState<UserInformationUiData>>()
    val userInformation: LiveData<ScreenState<UserInformationUiData>> get() = _userInformation

    fun getUserInfos(userId: String) {
        viewModelScope.launch {
            userInformationUseCase(userId).collect {
                when (it) {
                    NetworkResponseState.Loading -> _userInformation.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _userInformation.postValue(ScreenState.Success(userInformationMapper.map(it.result)))
                    is NetworkResponseState.Error -> _userInformation.postValue(ScreenState.Error(it.exception.message!!))
                }
            }
        }
    }

    fun getUserInfosFromFirebase(userMail: String) {
        _userInformation.value = ScreenState.Loading
        viewModelScope.launch {
            firebaseReadUserUseCase.invoke(
                userMail,
                onSuccess = {
                    _userInformation.postValue(ScreenState.Success(signUpUserEntityMapper.map(it)))
                },
                onFailure = {
                    _userInformation.postValue(ScreenState.Error(it))
                })
        }
    }
}
