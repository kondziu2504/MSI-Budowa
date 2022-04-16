package com.example.msi_budowa.common

data class ProductCategory(val name : String){
    override fun equals(other: Any?): Boolean {
        if(!(other is ProductCategory?))
            return false
        return name.equals(other!!.name, true)
    }

    override fun toString(): String {
        return name
    }
}
