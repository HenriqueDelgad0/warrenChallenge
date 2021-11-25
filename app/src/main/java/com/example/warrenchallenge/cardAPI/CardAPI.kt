package com.example.warrenchallenge.cardAPI

import retrofit2.Call
import retrofit2.http.*

interface CardAPI {
    @Headers("Access-token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcklkIjoiNTgzNGFkM2MyMjdlYTVhYzdmYzBmZjAyIiwiYWNjZXNzVG9rZW5JZCI6IjVkOWU0YzQ4NTE5NzIwMDAxMWJkN2YxOSIsImFjY2Vzc1Rva2VuSGFzaCI6ImQzOWM3NTY3MGQ5MzU4NmU0NDNhZDJiMjNjMGVlMWQzZDViOTdhMDcyYzU3NmYzNjY5NTA3Mjk4ZDUzYzlmN2UiLCJpYXQiOjE1NzA2NTUzMDQsImV4cCI6MTU3MTI2MDEwNH0.k4GtMCxIX8xP-K0yNW3SGKVbL0qT40EF8h-y9gWUE04")
    @GET("portfolios/mine")
    fun makeRequest(
        @Query("token")token: String
    ): Call<TokenData>
}

data class TokenData(
    val portfolios: List<Data>
)

data class Data(
    val _id: String,
    val name: String,
    val background: Background,
    val totalBalance: Float,
    val goalAmount: Double,
    val goalDate: String
)

data class Background(
    val thumb: String,
    val small: String,
    val full: String,
    val regular: String,
    val raw: String
)