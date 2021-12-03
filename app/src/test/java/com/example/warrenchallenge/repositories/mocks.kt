package com.example.warrenchallenge.repositories

import com.example.warrenchallenge.model.ErrorToken
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

fun mockException(): HttpException {
    val error = ErrorToken("any")
    val json = Gson().toJson(error)
    val responseBody = json.toResponseBody(null)
    val response = Response.error<Any>(400, responseBody)
    return HttpException(response)
}