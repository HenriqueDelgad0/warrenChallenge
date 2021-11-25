package com.example.warrenchallenge.card

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.warrenchallenge.cardAPI.CardRepository
import com.example.warrenchallenge.cardAPI.TokenData
import com.example.warrenchallenge.data.CallBack


class CardViewModel(private val repository: CardRepository) : AndroidViewModel(Application()){

    private val goals: MutableLiveData<TokenData> by lazy {
        MutableLiveData<TokenData>().also {
            loadGoals()
        }
    }

    fun getGoals(): LiveData<TokenData> {
        return goals
    }

    private fun loadGoals() {
        val tokenResult = getApplication<Application>().getSharedPreferences("sharedPrefs", MODE_PRIVATE)
            .getString("TOKEN", null).toString()

        repository.callRequest(tokenResult, object : CallBack<TokenData> {
            override fun onSuccessful(token: TokenData) {
                goals.value = token
            }

            override fun onFailure(t: Throwable) {

            }
        })
    }
}