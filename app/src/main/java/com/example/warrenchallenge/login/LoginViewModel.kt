package com.example.warrenchallenge.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.card.TokenRepository
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.model.Token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val enigmaticRepository: EnigmaticRepository,
    private val tokenRepository: TokenRepository
): ViewModel() {

    private var tokenResponse: MutableLiveData<String> = MutableLiveData()

    fun getTokenResponse(): LiveData<String> {
        return tokenResponse
    }

    fun loginRequest(login: String, password: String){
        viewModelScope.launch {
            val token = enigmaticRepository.callRequest(login, password)
            tokenRepository.saveTokenData(token.accessToken)
            tokenResponse.postValue(token.accessToken)
        }
    }

}