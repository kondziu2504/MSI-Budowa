package com.example.msi_budowa.common

import kotlin.collections.HashMap

data class CategoryTree(private val tree : HashMap<ProductCategory, List<ProductCategory>>){
    fun GetCategories() : List<ProductCategory> {
        return tree.keys.toList()
    }

    fun GetSubcategories(category: ProductCategory) : List<ProductCategory>{
        return tree[category]!!
    }
}
