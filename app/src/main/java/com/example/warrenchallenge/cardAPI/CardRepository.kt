package com.example.warrenchallenge.cardAPI

import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.model.ErrorToken
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardRepository(private val cardAPI: CardAPI) {
    fun callRequest(token: String, callBack: CallBack<TokenData>){
        cardAPI
            .makeRequest(token)
            .enqueue(object : retrofit2.Callback<TokenData> {
                override fun onResponse(call: Call<TokenData>, response: Response<TokenData>) {
                    if(response.code() == 200){
                        callBack.onSuccessful(checkNotNull(response.body()))
                    }else{
                        val gson = Gson()
                        val type = object : TypeToken<ErrorToken>() {}.type
                        val errorResponse: ErrorToken? = gson.fromJson(checkNotNull(response.errorBody()).charStream(), type)

                        callBack.onFailure(t = APIException(checkNotNull(errorResponse)))
                    }
                }

                override fun onFailure(call: Call<TokenData>, t: Throwable) {
                    callBack.onFailure(t)
                }
            })


    }
}