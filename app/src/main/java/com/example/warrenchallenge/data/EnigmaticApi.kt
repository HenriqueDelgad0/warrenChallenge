package com.example.warrenchallenge.data

import com.example.warrenchallenge.model.Token
import retrofit2.Call
import retrofit2.http.*

interface EnigmaticApi {
    @POST("account/login")
    suspend fun makeRequest(
        @Body postData: PostData
    ): Token
}

data class PostData (
    var email: String,
    var password: String
)