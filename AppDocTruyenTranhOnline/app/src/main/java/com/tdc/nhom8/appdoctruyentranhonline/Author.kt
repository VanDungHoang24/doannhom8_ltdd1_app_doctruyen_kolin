package com.tdc.nhom8.appdoctruyentranhonline
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    val id: String,
    var name: String,
    var bio: String,
    var avatar: String
) : Parcelable

