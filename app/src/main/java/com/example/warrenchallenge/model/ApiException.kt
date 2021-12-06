package com.example.warrenchallenge.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException

class ApiException(val errorToken: ErrorToken, cause: Throwable?) : Exception(cause)

fun HttpException.toApiException(): ApiException {
    val gson = Gson()
    val type = object : TypeToken<ErrorToken>() {}.type
    val errorBody = checkNotNull(response()?.errorBody())
    val errorToken = gson.fromJson<ErrorToken>(errorBody.charStream(), type)
    return ApiException(errorToken, this)
}

