package com.example.msi_budowa.common

import android.media.Image

data class Product (
    val name : String,
    val price: Price,
    val description : String,
    val category : ProductCategory? = null,
    val subcategory : ProductCategory? = null,
    val image: Image? = null)