package com.example.warrenchallenge.card

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenchallenge.login.LoginView
import com.example.warrenchallenge.databinding.CardActivityBinding
import com.example.warrenchallenge.recyclerView.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardView: AppCompatActivity() {
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
            val intent = Intent(this@CardView, LoginView::class.java)
            finish()
            this@CardView.startActivity(intent)
        }
    }
}