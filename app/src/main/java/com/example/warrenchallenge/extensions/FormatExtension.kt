package com.example.warrenchallenge.extensions

import android.R
import android.view.View
import java.text.DecimalFormat
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.lang.Double.*

fun Double.formatMoney(): String{
        val decimalFormat = DecimalFormat("###,###,##0.00")
        return decimalFormat.format(this)
}