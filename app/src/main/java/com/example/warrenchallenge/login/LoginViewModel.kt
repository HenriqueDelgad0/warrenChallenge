package com.example.warrenchallenge.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.card.TokenRepository
import com.example.warrenchallenge.core.Resource
import com.example.warrenchallenge.data.EnigmaticRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val enigmaticRepository: EnigmaticRepository,
    private val tokenRepository: TokenRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private var tokenResponse: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getTokenResponse(): LiveData<Resource<String>> {
        return tokenResponse
    }

    fun loginRequest(login: String, password: String) {
        viewModelScope.launch(dispatcher) {
            // trigger loading evento to the UI
            tokenResponse.postValue(Resource.Loading())

            // todo: choose one or another

            // transform API response to result
            val result: Result<String> = runCatching {
                val token = enigmaticRepository.callRequest(login, password)
                tokenRepository.saveTokenData(token.accessToken)
                token.accessToken
            }
            if (result.isSuccess) {
                tokenResponse.postValue(Resource.Success(result.getOrThrow()))
            } else {
                tokenResponse.postValue(Resource.Error(result.exceptionOrNull()))
            }
        }
    }

}