package com.example.warrenchallenge

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.account.AccountData
import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.databinding.LoginActivityBinding
import com.example.warrenchallenge.extensions.hideKeyboard
import com.example.warrenchallenge.model.Token
import com.example.warrenchallenge.token.TokenActivityView
import com.google.android.play.core.appupdate.v


class LoginActivity: AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private var enigmaticRepository = EnigmaticRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar

        val editText = findViewById<EditText>(R.id.passwordInput)

        val textView = findViewById(R.id.usernameInput) as AutoCompleteTextView
        val emails: Array<out String> = resources.getStringArray(R.array.emails_array)

        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, emails)
        textView.setAdapter(adapter)

        callLoginButton(editText)
        loginButton()
    }

    private fun loginButton() {
        binding.loginButton.setOnClickListener{
            loginRequest()
        }
    }

    private fun progressBarLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun callLoginButton(editText: EditText){
        val editText = findViewById<EditText>(R.id.passwordInput)
        editText.setOnEditorActionListener(TextView.OnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                loginRequest()
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun loginRequest(){
        val login = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        progressBarLoading()

        enigmaticRepository.callRequest(login, password, object : CallBack<Token>{
            override fun onSuccessful(token: Token) {
                val dataToSend = AccountData(login, password, token.accessToken)

                val intent = Intent(this@LoginActivity, TokenActivityView::class.java)
                intent.putExtra("henrique", dataToSend)
                this@LoginActivity.startActivity(intent)
                stopLoading()
            }

            override fun onFailure(t: Throwable) {
                if(t is APIException) {
                    Toast.makeText(this@LoginActivity, t.errorToken.error, Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this@LoginActivity, "Deu ruim", Toast.LENGTH_SHORT).show()
                }
                stopLoading()
            }
        })
    }

}
