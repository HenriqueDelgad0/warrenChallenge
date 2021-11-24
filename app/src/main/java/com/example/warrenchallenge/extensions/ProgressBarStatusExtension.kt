package com.example.warrenchallenge.extensions

import android.widget.ProgressBar
import com.example.warrenchallenge.R
import com.example.warrenchallenge.cardsAPI.Data

fun calculateProgressBar(goalAmount: Double, totalBalance: Float) : Double{
    return (totalBalance / goalAmount) * 100
}