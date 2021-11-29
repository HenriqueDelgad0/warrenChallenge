package com.example.warrenchallenge.extensions

fun calculateProgressBar(totalBalance: Float, goalAmount: Double?) : Double{
    if(goalAmount != null){
        val progressValue = totalBalance / goalAmount!! * 100
        if(progressValue > 0 && progressValue < 1){
            return 1.0
        }else {
            return progressValue
        }
    } else {
        return 0.0
    }
}