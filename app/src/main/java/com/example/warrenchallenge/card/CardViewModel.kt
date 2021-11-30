package com.example.warrenchallenge.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.warrenchallenge.cardAPI.CardRepository
import com.example.warrenchallenge.cardAPI.TokenData
import com.example.warrenchallenge.data.CallBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val repository: CardRepository,
    private val tokenRepository: TokenRepository
): ViewModel(){


    private val goals: MutableLiveData<TokenData> by lazy {
        MutableLiveData<TokenData>().also {
            loadGoals()
        }
    }

    private fun loadGoals() {
        repository.callRequest(tokenRepository.getApiToken()!!, object : CallBack<TokenData> {
            override fun onSuccessful(token: TokenData) {
                goals.value = token
            }

            override fun onFailure(t: Throwable) {

            }
        })
    }

    fun hasDate() = tokenRepository.hasData()

    fun getGoals(): LiveData<TokenData> {
        return goals
    }
}