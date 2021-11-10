package com.example.warrenchallenge.token

import android.util.Log
import androidx.lifecycle.ViewModel

class TokenViewModel(token: String) : ViewModel() {
    var token_result = token

    init{
        Log.i("Teste", "O token é $token_result")
    }

}