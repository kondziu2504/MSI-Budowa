package com.example.msi_budowa.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductCategory(val name : String) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if(!(other is ProductCategory?))
            return false
        return name.equals(other!!.name, true)
    }

    override fun toString(): String {
        return name
    }
}
