package com.example.msi_budowa.common.data_source

import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product

object Repository {

    private val dataSource : IDataSource = MockDataSource()

    fun GetWarehouseItems(onSuccess: (List<Product>) -> Unit){
        return dataSource.GetWarehouseItems(onSuccess)
    }

    fun GetCategories(onSuccess: (CategoryTree) -> Unit){
        return dataSource.GetCategories(onSuccess)
    }
}