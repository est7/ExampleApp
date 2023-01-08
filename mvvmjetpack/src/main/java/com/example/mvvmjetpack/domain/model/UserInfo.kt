package com.example.mvvmjetpack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Gender : Parcelable {
    object Man : Gender()
    object Woman : Gender()
    object Unknown : Gender()
}

@Parcelize
data class UserInfo(val token: String, val name: String, val gender: Gender) : Parcelable