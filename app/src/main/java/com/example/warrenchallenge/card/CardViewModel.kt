package com.example.warrenchallenge.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.cardAPI.CardRepository
import com.example.warrenchallenge.cardAPI.TokenData
import com.example.warrenchallenge.data.CallBack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            val token = repository.callRequest(tokenRepository.getApiToken()!!)
            goals.value  = token
        }
    }

    fun hasDate() = tokenRepository.hasData()

    fun getGoals(): LiveData<TokenData> {
        return goals
    }
}