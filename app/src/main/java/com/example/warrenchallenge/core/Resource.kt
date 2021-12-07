package com.example.warrenchallenge.core

/**
 * Representation of all states allowed to a API request
 */
sealed class Resource<T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Error<T>(val throwable: Throwable?) : Resource<T>()

    class Loading<T> : Resource<T>()

}

