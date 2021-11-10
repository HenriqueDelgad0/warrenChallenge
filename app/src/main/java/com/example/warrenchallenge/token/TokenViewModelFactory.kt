package com.example.warrenchallenge.token

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TokenViewModelFactory(private val token: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TokenViewModel::class.java)){
            return TokenViewModel(token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}