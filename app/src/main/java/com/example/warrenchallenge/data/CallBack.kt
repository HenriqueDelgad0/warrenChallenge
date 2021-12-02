package com.example.warrenchallenge.data

import com.example.warrenchallenge.model.Token

interface CallBack<T> {
    fun onSuccessful(token: T): String

    fun onFailure(t: Throwable)
}