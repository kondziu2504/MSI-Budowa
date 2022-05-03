package com.example.msi_budowa.orders

import android.os.Parcel
import android.os.Parcelable

class Order(val id: Long, val title: String?, val orderingName: String?, val address: String?, val status: OrderStatus, val description: String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        OrderStatus.values()[parcel.readInt()],
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(orderingName)
        parcel.writeString(address)
        val statusTmp = if (status == null) -1 else status.ordinal
        parcel.writeInt(statusTmp)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }


}