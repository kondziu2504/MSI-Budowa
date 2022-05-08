package com.example.msi_budowa.common.data_source

import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.orders.Order

object Repository {

    private val dataSource : IDataSource = MockDataSource()

    fun GetWarehouseItems(onSuccess: (List<Product>) -> Unit){
        dataSource.GetWarehouseItems(onSuccess)
    }

    fun GetCategories(onSuccess: (CategoryTree) -> Unit){
        dataSource.GetCategories(onSuccess)
    }

    fun GetOrders(onSuccess: (List<Order>) -> Unit) {
        dataSource.GetOrders(onSuccess)
    }

    fun UpdateOrder(newOrder : Order, onSuccess: () -> Unit, onFailure: () -> Unit){
        onSuccess()
    }
}