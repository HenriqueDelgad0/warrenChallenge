package com.example.warrenchallenge.extensions

import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    val view: View? = this.findViewById(R.id.content)
    if (view != null) {
        val imm: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}