package com.mustafaunlu.ecommerce.utils

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mustafaunlu.ecommerce.common.Constants
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val sharedPref: SharedPreferences,
) {
    private val _isTokenValid = MutableLiveData(isTokenValid())
    val isTokenValid: LiveData<Boolean> get() = _isTokenValid
    fun saveToken(token: String, expirationTime: Long) {
        sharedPref.edit().apply {
            putString(Constants.USER_TOKEN, token).apply()
            putLong(Constants.USER_TOKEN_EXPIRATION_TIME, expirationTime).apply()
        }
    }

    fun checkTokenValidity() {
        val isTokenValid = (System.currentTimeMillis() / 1000) < getTokenExpirationTime()
        _isTokenValid.value = isTokenValid
    }

    fun getToken(): String? {
        return sharedPref.getString(Constants.USER_TOKEN, null)
    }

    private fun getTokenExpirationTime(): Long {
        return sharedPref.getLong(Constants.USER_TOKEN_EXPIRATION_TIME, 0)
    }

    fun deleteToken() {
        sharedPref.edit().remove(Constants.USER_TOKEN).apply()
        sharedPref.edit().remove(Constants.USER_TOKEN_EXPIRATION_TIME).apply()
    }

    fun isTokenValid(): Boolean {
        val expirationTime = getTokenExpirationTime()
        Log.d("TokenManager", "Token system time: ${System.currentTimeMillis() / 1000} < Token expiration time: $expirationTime")
        return (System.currentTimeMillis() / 1000) < expirationTime
    }
}
