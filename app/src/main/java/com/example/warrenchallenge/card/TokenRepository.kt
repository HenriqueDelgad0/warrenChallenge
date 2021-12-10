package com.example.warrenchallenge.card

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

interface TokenRepository {

    fun getApiToken(): String?

    fun hasData(): Boolean

    fun saveTokenData(
        token: String,
    )
}

class SharedPreferencesTokenRepository @Inject constructor(private val sharedPreferences: SharedPreferences) : TokenRepository {
    override fun getApiToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    override fun hasData(): Boolean {
        return sharedPreferences.contains("TOKEN")
    }

    override fun saveTokenData(token: String) {
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("TOKEN", token)
        }
    }
}

