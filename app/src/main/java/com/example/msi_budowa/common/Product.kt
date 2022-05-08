package com.example.msi_budowa.common

import android.media.Image
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Product (
    val id : Long,
    val name : String,
    val price: Price,
    val description : String,
    val category : ProductCategory? = null,
    val subcategory : ProductCategory? = null,
    val image: Image? = null)