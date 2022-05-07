package com.example.msi_budowa.common.data_source

import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.orders.Order

interface IDataSource {
    public fun GetWarehouseItems(onSuccess : (List<Product>) -> Unit)
    public fun GetCategories(onSuccess: (CategoryTree) -> Unit)
    public fun GetOrders(onSuccess: (List<Order>) -> Unit)
}