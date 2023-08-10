package com.mustafaunlu.ecommerce.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.firebase.read_user.ReadFirebaseUserInfosUseCase
import com.mustafaunlu.ecommerce.domain.usecase.user.ReadApiUserInfosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userInformationUseCase: ReadApiUserInfosUseCase,
    private val readFirebaseUserInfosUseCase: ReadFirebaseUserInfosUseCase,
    private val firebaseUserInfoToUiData: ProductBaseMapper<UserInformationEntity, FirebaseUserUiData>,
) : ViewModel() {
    private val _userInfos = MutableLiveData<ScreenState<FirebaseUserUiData>>()
    val userInfos: LiveData<ScreenState<FirebaseUserUiData>> get() = _userInfos

    fun getUserInfosFromApi(userId: String) {
        viewModelScope.launch {
            userInformationUseCase(userId).collect {
                when (it) {
                    NetworkResponseState.Loading -> _userInfos.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _userInfos.postValue(ScreenState.Success(firebaseUserInfoToUiData.map(it.result)))
                    is NetworkResponseState.Error -> _userInfos.postValue(ScreenState.Error(it.exception.message!!))
                }
            }
        }
    }

    fun getUserInfosFromFirebase(userMail: String) {
        _userInfos.value = ScreenState.Loading
        viewModelScope.launch {
            readFirebaseUserInfosUseCase.invoke(
                userMail,
                onSuccess = {
                    _userInfos.postValue(ScreenState.Success(firebaseUserInfoToUiData.map(it)))
                }
            ) {
                _userInfos.postValue(ScreenState.Error(it))
            }
        }
    }
}
