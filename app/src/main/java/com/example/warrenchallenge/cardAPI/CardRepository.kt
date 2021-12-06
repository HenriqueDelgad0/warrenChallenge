package com.example.warrenchallenge.cardAPI

import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.model.ErrorToken
import com.example.warrenchallenge.model.Token
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class CardRepository @Inject constructor(private val cardAPI: CardAPI){
    suspend fun callRequest(token: String): TokenData {
        return cardAPI
            .makeRequest(token)
    }
}