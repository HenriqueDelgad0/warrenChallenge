package com.example.warrenchallenge.account

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class AccountData(
    val login: String,
    val password: String,
    val token: String) : Parcelable {
}

class UserData(
    val login: String,
    val password: String
)