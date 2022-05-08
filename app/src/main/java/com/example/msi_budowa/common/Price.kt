package com.example.msi_budowa.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(val value : Float, val priceType: PriceType) : Parcelable