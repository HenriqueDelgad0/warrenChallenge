package com.example.warrenchallenge.card

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenchallenge.LoginActivity
import com.example.warrenchallenge.cardAPI.CardAPI
import com.example.warrenchallenge.cardAPI.CardRepository
import com.example.warrenchallenge.data.PostData
import com.example.warrenchallenge.databinding.CardActivityBinding
import com.example.warrenchallenge.recyclerView.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class CardActivityView: AppCompatActivity() {
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

        viewModel.getGoals().observe(this, { tokenData ->
            adapter.cardData.addAll(tokenData.portfolios)
            adapter.notifyDataSetChanged()
        })

        if(!viewModel.hasDate()){
            val intent = Intent(this@CardActivityView, LoginActivity::class.java)
            finish()
            this@CardActivityView.startActivity(intent)
        }
    }

    private fun createCardApi(): CardAPI {
        return Retrofit.Builder()
            .baseUrl("https://enigmatic-bayou-48219.herokuapp.com/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardAPI::class.java)
    }

    private fun createSharedPrefences(): SharedPreferences {
        return getSharedPreferences("sharedPrefs", MODE_PRIVATE)
    }
}