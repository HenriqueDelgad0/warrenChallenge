package com.example.warrenchallenge.extensions

fun calculateProgressBar(goalAmount: Double, totalBalance: Float) : Double{
    return (totalBalance / goalAmount) * 100
}