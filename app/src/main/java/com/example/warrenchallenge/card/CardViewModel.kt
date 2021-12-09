package com.example.warrenchallenge.card

import android.media.session.MediaSession
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.cardAPI.CardRepository
import com.example.warrenchallenge.cardAPI.Data
import com.example.warrenchallenge.cardAPI.TokenData
import com.example.warrenchallenge.core.Resource
import com.example.warrenchallenge.data.CallBack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val repository: CardRepository,
    private val tokenRepository: TokenRepository
): ViewModel(){

    private val cardResponse: MutableLiveData<Resource<TokenData>> by lazy {
        MutableLiveData<Resource<TokenData>>().also {
            loadGoals()
        }
    }

    fun getCardResponse(): LiveData<Resource<TokenData>> {
        return cardResponse
    }

    private fun loadGoals() {
        viewModelScope.launch {
            cardResponse.postValue(Resource.Loading())

            val result: Result<TokenData> = runCatching {
                val tokenData = repository.callRequest(tokenRepository.getApiToken()!!)
                tokenData
            }
            if (result.isSuccess) {
                cardResponse.postValue(Resource.Success(result.getOrThrow()))
            } else {
                cardResponse.postValue((Resource.Error(result.exceptionOrNull())))
            }
        }
    }

    fun hasDate() = tokenRepository.hasData()

}