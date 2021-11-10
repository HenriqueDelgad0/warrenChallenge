package com.example.warrenchallenge.token

import android.annotation.SuppressLint
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

        val result = intent.getParcelableExtra<AccountData>("henrique")

        binding.resultEmail.text = "Login: ${result!!.login}"
        binding.resultPassword.text = "Password: ${result.password}"
        binding.resultToken.text = "Token: ${result.token}"


    }




}