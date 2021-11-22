package com.example.warrenchallenge.token

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.warrenchallenge.LoginActivity
import com.example.warrenchallenge.account.AccountData
import com.example.warrenchallenge.databinding.TokenActivityBinding

class TokenActivityView(): AppCompatActivity() {
    private lateinit var binding: TokenActivityBinding
    private lateinit var viewModel: TokenViewModel
    private lateinit var viewModelFactory: TokenViewModelFactory

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TokenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        if(!hasData()){
            val intent = Intent(this@TokenActivityView, LoginActivity::class.java)
            finish()
            this@TokenActivityView.startActivity(intent)
        }

        binding.resultEmail.text = "Login: ${sharedPreferences.getString("EMAIL", null)}"
        binding.resultPassword.text = "Password: ${sharedPreferences.getString("PASSWORD", null)}"
        binding.resultToken.text = "Token: ${sharedPreferences.getString("TOKEN", null)}"

//        val result = intent.getParcelableExtra<AccountData>("henrique")
//
//        binding.resultEmail.text = "Login: ${result!!.login}"
//        binding.resultPassword.text = "Password: ${result.password}"
//        binding.resultToken.text = "Token: ${result.token}"

    }

    private fun hasData(): Boolean{
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.contains("TOKEN")
    }


}