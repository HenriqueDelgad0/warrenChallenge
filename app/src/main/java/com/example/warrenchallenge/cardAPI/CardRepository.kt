package com.example.warrenchallenge.cardAPI

import com.example.warrenchallenge.core.Resource
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.data.PostData
import com.example.warrenchallenge.model.ErrorToken
import com.example.warrenchallenge.model.FeatureException
import com.example.warrenchallenge.model.toApiException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class CardRepository @Inject constructor(private val cardAPI: CardAPI){
    suspend fun callRequest(token: String): TokenData {
        return try {
            if(token.isBlank()) {
                throw FeatureException("Token should not be blank")
            }
            cardAPI.makeRequest(token)
        } catch (e: HttpException) {
            throw e.toApiException()
        }
    }
}
