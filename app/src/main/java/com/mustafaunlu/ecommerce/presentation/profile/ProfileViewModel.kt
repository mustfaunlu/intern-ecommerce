package com.mustafaunlu.ecommerce.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.common.UserInformationUiData
import com.mustafaunlu.ecommerce.domain.entity.UserInformationEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce.domain.usecase.user.UserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userInformationUseCase: UserInfoUseCase,
    private val userInformationMapper: ProductBaseMapper<UserInformationEntity, UserInformationUiData>,
) : ViewModel() {
    private val _userInformation = MutableLiveData<ScreenState<UserInformationUiData>>()
    val userInformation: LiveData<ScreenState<UserInformationUiData>> get() = _userInformation

    fun getUserInfos(userId: Int) {
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
}
