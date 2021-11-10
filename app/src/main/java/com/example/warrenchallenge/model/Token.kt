package com.example.warrenchallenge.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
)

data class ErrorToken(
    val error: String,
)