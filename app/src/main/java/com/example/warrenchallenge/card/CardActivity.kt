package com.example.warrenchallenge.card

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenchallenge.cardAPI.Data
import com.example.warrenchallenge.core.Resource
import com.example.warrenchallenge.login.LoginActivity
import com.example.warrenchallenge.databinding.CardActivityBinding
import com.example.warrenchallenge.model.ApiException
import com.example.warrenchallenge.recyclerView.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardActivity: AppCompatActivity() {
    private lateinit var binding: CardActivityBinding
    private val viewModel: CardViewModel by viewModels()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerAdapter

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CardActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

        viewModel.getCardResponse().observe(this, { tokenData ->
            when(tokenData){
                is Resource.Error -> handleError(tokenData.throwable)
                is Resource.Loading -> progressBarLoading()
                is Resource.Success -> handleSuccess(tokenData.data.portfolios)
            }
        })

        if(!viewModel.hasDate()){
            val intent = Intent(this@CardActivity, LoginActivity::class.java)
            finish()
            this@CardActivity.startActivity(intent)
        }
    }

    private fun handleError(throwable: Throwable?){
        Log.e("test", "an error happens", throwable)
        if (throwable is ApiException) {
            Toast.makeText(this, throwable.errorToken.error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Tente novamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun progressBarLoading(){
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleSuccess(portfolios: List<Data>) {
        adapter.cardData.addAll(portfolios)
        adapter.notifyDataSetChanged()
    }
}