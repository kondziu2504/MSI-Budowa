package com.example.msi_budowa.orders

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Order(val id: Long,
            val title: String?,
            val orderingName: String?,
            val address: String?,
            val status: OrderStatus,
            val description: String?,
            val products : Map<Long, Int>) : Parcelable
