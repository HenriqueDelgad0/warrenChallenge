package com.example.warrenchallenge.token

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenchallenge.LoginActivity
import com.example.warrenchallenge.R
import com.example.warrenchallenge.account.AccountData
import com.example.warrenchallenge.cardsAPI.CardRepository
import com.example.warrenchallenge.cardsAPI.TokenData
import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.databinding.TokenActivityBinding
import com.example.warrenchallenge.model.Token
import com.example.warrenchallenge.recyclerView.RecyclerAdapter

class TokenActivityView(): AppCompatActivity() {
    private lateinit var binding: TokenActivityBinding
    private val cardRepository = CardRepository()

    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TokenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

        if(!hasData()){
            val intent = Intent(this@TokenActivityView, LoginActivity::class.java)
            finish()
            this@TokenActivityView.startActivity(intent)
        }

        cardImageRequest()


    }

    private fun hasData(): Boolean{
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.contains("TOKEN")
    }

    private fun cardImageRequest(){
        val token = getSharedPreferences("sharedPrefs", MODE_PRIVATE).getString("TOKEN", null).toString()

        cardRepository.callRequest(token, object : CallBack<TokenData>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onSuccessful(token: TokenData) {
                adapter.cardData.addAll(token.portfolios)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(t: Throwable) {
                if(t is APIException) {
                    Toast.makeText(this@TokenActivityView, t.errorToken.error, Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this@TokenActivityView, "Deu ruim", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}