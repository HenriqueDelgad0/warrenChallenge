package com.example.warrenchallenge.data

import android.content.SharedPreferences
import com.example.warrenchallenge.card.TokenRepository
import com.example.warrenchallenge.model.ErrorToken
import com.example.warrenchallenge.model.Token
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class EnigmaticRepository @Inject constructor(private val enigmaticApi: EnigmaticApi){
    suspend fun callRequest(email : String, password : String): Token {
        return enigmaticApi
            .makeRequest(PostData(email, password))
    }
}