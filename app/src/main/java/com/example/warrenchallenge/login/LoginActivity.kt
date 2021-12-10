package com.example.warrenchallenge.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.R
import com.example.warrenchallenge.card.CardActivity
import com.example.warrenchallenge.core.Resource
import com.example.warrenchallenge.databinding.LoginActivityBinding
import com.example.warrenchallenge.extensions.hideKeyboard
import com.example.warrenchallenge.model.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
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

        viewModel.getTokenResponse().observe(this, { resource ->
            when (resource) {
                is Resource.Error -> handleError(resource.throwable)
                is Resource.Loading -> progressBarLoading()
                is Resource.Success -> handleSuccess()
            }
        })

        callLoginButton()

        binding.loginButton.setOnClickListener{
            loginButton()
        }
    }

    private fun handleError(throwable: Throwable?) {
        Log.e("test", "an error happens", throwable)
        stopLoading()
        if (throwable is ApiException) {
            Toast.makeText(this, throwable.errorToken.error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Tente novamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleSuccess() {
        stopLoading()
        goToCardActivity()
    }

    private fun loginButton() {
        val login = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        viewModel.loginRequest(login, password)
    }

    private fun goToCardActivity() {
        val intent = Intent(this@LoginActivity, CardActivity::class.java)
        finish()
        this@LoginActivity.startActivity(intent)
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
