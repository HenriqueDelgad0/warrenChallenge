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

        viewModel.getTokenResponse().observe(this, {
            goToCardActivity()
            stopLoading()
        })

        callLoginButton()

        binding.loginButton.setOnClickListener{
            progressBarLoading()
            loginButton()
        }
    }

    private fun loginButton() {
        val login = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        viewModel.loginRequest(login, password)
    }

    private fun goToCardActivity() {
        val intent = Intent(this@LoginView, CardView::class.java)
        finish()
        this@LoginView.startActivity(intent)
    }

    private fun callLoginButton(){
        val getEditText = binding.passwordInput
        getEditText.setOnEditorActionListener(TextView.OnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                loginButton()
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun progressBarLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        binding.progressBar.visibility = View.INVISIBLE
    }
}
