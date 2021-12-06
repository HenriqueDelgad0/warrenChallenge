package com.example.warrenchallenge.extensions

import java.text.DecimalFormat

fun Double.formatMoney(): String {
    val decimalFormat = DecimalFormat("R$ ###,###,##0.00")
    return decimalFormat.format(this)
}
