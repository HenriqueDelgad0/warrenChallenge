package com.example.warrenchallenge.card

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenchallenge.LoginActivity
import com.example.warrenchallenge.cardAPI.CardRepository
import com.example.warrenchallenge.cardAPI.TokenData
import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.databinding.CardActivityBinding
import com.example.warrenchallenge.recyclerView.RecyclerAdapter

class CardActivityView(): AppCompatActivity() {
    private lateinit var binding: CardActivityBinding
    private lateinit var viewModel: CardViewModel

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

        if(!hasData()){
            val intent = Intent(this@CardActivityView, LoginActivity::class.java)
            finish()
            this@CardActivityView.startActivity(intent)
        }

        viewModel = ViewModelProvider(this).get(CardViewModel :: class.java)
        viewModel.getGoals().observe(this, { tokenData ->
            adapter.cardData.addAll(tokenData.portfolios)
            adapter.notifyDataSetChanged()
        })


    }

    private fun hasData(): Boolean{
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        return sharedPreferences.contains("TOKEN")
    }


    //    private fun cardImageRequest(){
//        val token = getSharedPreferences("sharedPrefs", MODE_PRIVATE).getString("TOKEN", null).toString()
//
//        cardRepository.callRequest(token, object : CallBack<TokenData>{
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onSuccessful(token: TokenData) {
//                adapter.cardData.addAll(token.portfolios)
//                adapter.notifyDataSetChanged()
//            }
//
//            override fun onFailure(t: Throwable) {
//                if(t is APIException) {
//                    Toast.makeText(this@TokenActivityView, t.errorToken.error, Toast.LENGTH_SHORT).show()
//                }else {
//                    Toast.makeText(this@TokenActivityView, "Deu ruim", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }
}