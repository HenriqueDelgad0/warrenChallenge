package com.example.warrenchallenge.data

import com.example.warrenchallenge.model.FeatureException
import com.example.warrenchallenge.model.Token
import com.example.warrenchallenge.model.toApiException
import retrofit2.HttpException

import javax.inject.Inject

class EnigmaticRepository @Inject constructor(private val enigmaticApi: EnigmaticApi){
    suspend fun callRequest(email : String, password : String): Token {
        return try {
            if (password.isBlank()) {
                throw FeatureException("password should not be blank")
            }
            enigmaticApi.makeRequest(PostData(email, password))
        } catch (e: HttpException) {
            throw e.toApiException()
        }
    }
}

