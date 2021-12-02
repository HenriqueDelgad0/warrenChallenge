package com.example.warrenchallenge.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.R
import com.example.warrenchallenge.account.AccountData
import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.databinding.LoginActivityBinding
import com.example.warrenchallenge.extensions.hideKeyboard
import com.example.warrenchallenge.model.Token
import com.example.warrenchallenge.card.CardView
import com.example.warrenchallenge.card.TokenRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginView: AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = findViewById<AutoCompleteTextView>(R.id.usernameInput)
        val emails: Array<out String> = resources.getStringArray(R.array.emails_array)

        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, emails)
        textView.setAdapter(adapter)

        callLoginButton()

        binding.loginButton.setOnClickListener{
            functionLogin()
        }
    }

    private fun functionLogin() {
        val login = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        viewModel.loginRequest(login, password)

        viewModel.getTokenResponse().observe(this, { response ->
            when(response) {
                is String -> {
                    if(response != null) {
                        saveLoginData(login, password, response)
                        goToCardActivity()
                    }
                }
            }
        })
    }

    private fun goToCardActivity() {
        val intent = Intent(this@LoginView, CardView::class.java)
        finish()
        this@LoginView.startActivity(intent)
    }

    // DELGADO
    /*private fun loginButton() {
        val login = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        saveLoginData(login, password)
        println("SaveLoginData: " + login + password)
        binding.loginButton.setOnClickListener{

            viewModel.loginRequest(login,password)

            if(viewModel.getTokenResponse() != null){
                val intent = Intent(this@LoginView, CardView::class.java)
                finish()
                this@LoginView.startActivity(intent)
            }
        }
        progressBarLoading()
        stopLoading()
    }*/

    private fun callLoginButton(){
        val getEditText = binding.passwordInput
        getEditText.setOnEditorActionListener(TextView.OnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                //loginButton()
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun saveLoginData(login: String, password: String, token: String){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("EMAIL", login)
            putString("PASSWORD", password)
            putString("TOKEN", token)
        }.apply()
    }


    private fun progressBarLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        binding.progressBar.visibility = View.INVISIBLE
    }
}
