package com.tdc.nhom8.appdoctruyentranhonline

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

    @Parcelize
    data class Comic(
        val id: String,
        var name: String,
        var author: Author,
        var price: Double,
        var coverImage: String,

        var description: String
    ) : Parcelable
