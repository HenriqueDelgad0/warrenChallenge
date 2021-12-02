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
    fun callRequest(email : String, password : String, callBack: CallBack<Token>) {
        val baseUrl = "https://enigmatic-bayou-48219.herokuapp.com/api/v2/"

        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(EnigmaticApi::class.java)
            .makeRequest(PostData(email, password))
            .enqueue(object : Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if(response.code() == 200){
                        callBack.onSuccessful(checkNotNull(response.body()))
                    }else{
                        val gson = Gson()
                        val type = object : TypeToken<ErrorToken>() {}.type
                        val errorResponse: ErrorToken? = gson.fromJson(checkNotNull(response.errorBody()).charStream(), type)

                        callBack.onFailure(t = APIException(checkNotNull(errorResponse)))
                    }
                }

                override fun onFailure(call: Call<Token>, t: Throwable) {
                    callBack.onFailure(t)
                }
            })
    }
}

class APIException(val errorToken: ErrorToken) : Exception("Invalid exception"){

}

class sharedPreferencesLoginData @Inject constructor(private val sharedPreferences: SharedPreferences) :
    TokenRepository {
    override fun getApiToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    override fun hasData(): Boolean {
        return sharedPreferences.contains("TOKEN")
    }
}