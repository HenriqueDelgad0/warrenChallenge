package com.example.warrenchallenge.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.warrenchallenge.R
import com.example.warrenchallenge.cardsAPI.CardRepository
import com.example.warrenchallenge.cardsAPI.Data
import com.example.warrenchallenge.extensions.formatMoney
import com.example.warrenchallenge.cardsAPI.TokenData
import com.example.warrenchallenge.data.APIException
import com.example.warrenchallenge.data.CallBack
import com.example.warrenchallenge.extensions.calculateProgressBar
import com.google.gson.internal.bind.util.ISO8601Utils.format
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.lang.String.format
import java.text.DecimalFormat
import java.text.MessageFormat.format

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val cardData = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val auxiliar = cardData[position]
        holder.itemTitle.text = auxiliar.name
        holder.itemMoney.text = auxiliar.goalAmount.formatMoney()
        Picasso.get().load(auxiliar.background.regular).resize(328,200)
            .centerCrop().into(holder.itemImage)
        holder.progressBar.progress = calculateProgressBar(auxiliar.goalAmount, auxiliar.totalBalance).toInt()
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.backgroundImage)
        var itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        var itemMoney: TextView = itemView.findViewById(R.id.itemMoneyValue)
        var progressBar: ProgressBar = itemView.findViewById(R.id.horizontalProgressbar)
    }



}
