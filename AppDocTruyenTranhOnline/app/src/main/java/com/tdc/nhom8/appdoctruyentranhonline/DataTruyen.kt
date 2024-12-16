package com.tdc.nhom8.appdoctruyentranhonline

import android.os.Parcel
import android.os.Parcelable

data class DataTruyen(
    val tenTruyen: String,
    val tacGia: String,
    val gia: String,
    val imageResId: Int,
    val soLuong: Int,
    var isChecked: Boolean // Change 'val' to 'var' to make it mutable
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte() // Handle the boolean value properly
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tenTruyen)
        parcel.writeString(tacGia)
        parcel.writeString(gia)
        parcel.writeInt(imageResId)
        parcel.writeInt(soLuong)
        parcel.writeByte(if (isChecked) 1 else 0) // Write boolean as byte
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataTruyen> {
        override fun createFromParcel(parcel: Parcel): DataTruyen {
            return DataTruyen(parcel)
        }

        override fun newArray(size: Int): Array<DataTruyen?> {
            return arrayOfNulls(size)
        }
    }
}
