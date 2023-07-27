package com.mustafaunlu.ecommerce.utils

import android.content.SharedPreferences
import android.util.Log
import com.mustafaunlu.ecommerce.common.Constants
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val sharedPref: SharedPreferences,
) {
    fun saveToken(token: String, expirationTime: Long) {
        sharedPref.edit().apply {
            putString(Constants.USER_TOKEN, token).apply()
            putLong(Constants.USER_TOKEN_EXPIRATION_TIME, expirationTime).apply()
        }
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
        Log.d("TokenManager", "Token is valid: ${System.currentTimeMillis() / 1000 < expirationTime}")
        return (System.currentTimeMillis() / 1000) < expirationTime
    }
}
