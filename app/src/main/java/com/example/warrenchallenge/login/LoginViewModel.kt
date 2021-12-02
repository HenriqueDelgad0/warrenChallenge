package com.example.warrenchallenge.login

import android.content.Context
import android.content.Intent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.warrenchallenge.account.AccountData
import com.example.warrenchallenge.card.CardView
import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.extensions.hideKeyboard
import com.example.warrenchallenge.model.Token
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val enigmaticRepository: EnigmaticRepository
): ViewModel() {

    private val userData: MutableLiveData<AccountData> by lazy {
        MutableLiveData<AccountData>()
    }

    fun loginRequest(login: String, password: String){
        enigmaticRepository.callRequest(login, password, object : CallBack<Token> {
            override fun onSuccessful(token: Token): String {
                val dataToSend = token.accessToken
                hasToken(dataToSend)
                return token.accessToken
            }

            override fun onFailure(t: Throwable) {

            }
        })
    }

    fun callLoginRequest(){

    }

    fun hasToken(token: String?): String?{
        return token
    }

}