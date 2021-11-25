package com.example.warrenchallenge.card

import android.content.SharedPreferences

interface TokenRepository {

    fun getApiToken(): String?

    fun hasData(): Boolean

}

class SharedPreferencesTokenRepository(private val sharedPreferences: SharedPreferences) : TokenRepository {
    override fun getApiToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    override fun hasData(): Boolean {
        return sharedPreferences.contains("TOKEN")
    }
}

class HardCodedTokenRepository : TokenRepository {
    override fun getApiToken(): String {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcklkIjoiNTgzNGFkM2MyMjdlYTVhYzdmYzBmZjAyIiwiYWNjZXNzVG9rZW5JZCI6IjVkOWU0YzQ4NTE5NzIwMDAxMWJkN2YxOSIsImFjY2Vzc1Rva2VuSGFzaCI6ImQzOWM3NTY3MGQ5MzU4NmU0NDNhZDJiMjNjMGVlMWQzZDViOTdhMDcyYzU3NmYzNjY5NTA3Mjk4ZDUzYzlmN2UiLCJpYXQiOjE1NzA2NTUzMDQsImV4cCI6MTU3MTI2MDEwNH0.k4GtMCxIX8xP-K0yNW3SGKVbL0qT40EF8h-y9gWUE04"
    }

    override fun hasData(): Boolean = true

}