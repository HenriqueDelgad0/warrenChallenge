package com.example.warrenchallenge.card

import android.content.SharedPreferences
import javax.inject.Inject

interface TokenRepository {

    fun getApiToken(): String?

    fun hasData(): Boolean

}

class SharedPreferencesTokenRepository @Inject constructor(private val sharedPreferences: SharedPreferences) : TokenRepository {
    override fun getApiToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    override fun hasData(): Boolean {
        return sharedPreferences.contains("TOKEN")
    }
}

interface LoginData {

    fun getLoginData(): LoginData

    fun hasData(): Boolean
}

