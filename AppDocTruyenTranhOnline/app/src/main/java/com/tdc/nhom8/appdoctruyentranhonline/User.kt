package com.tdc.nhom8.appdoctruyentranhonline

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    var name: String,
    var password: String,
    var email: String,
    var userCoverImage: String
) : Parcelable