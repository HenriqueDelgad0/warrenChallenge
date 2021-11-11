package com.example.warrenchallenge

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.account.AccountData
import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.databinding.LoginActivityBinding
import com.example.warrenchallenge.model.Token
import com.example.warrenchallenge.token.TokenActivityView


class LoginActivity: AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private var enigmaticRepository = EnigmaticRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{

            val login = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            enigmaticRepository.callRequest(login, password, object : CallBack<Token>{
                override fun onSuccessful(token: Token) {
                    val dataToSend = AccountData(login, password, token.accessToken)

                    val intent = Intent(this@LoginActivity, TokenActivityView::class.java)
                    intent.putExtra("henrique", dataToSend)
                    this@LoginActivity.startActivity(intent)
                }

                override fun onFailure(t: Throwable) {
                    if(t is APIException) {
                        Toast.makeText(this@LoginActivity, t.errorToken.error, Toast.LENGTH_SHORT).show()
                    }else {

                        Toast.makeText(this@LoginActivity, "Deu ruim", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }

    }
}
