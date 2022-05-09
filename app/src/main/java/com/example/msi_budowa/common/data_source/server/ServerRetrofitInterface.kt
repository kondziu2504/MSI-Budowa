package com.example.msi_budowa.common.data_source.server

import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.orders.Order
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerRetrofitInterface
{
    @GET("products")
    public fun GetWarehouseItems() : Call<List<Product>>

    @GET("categories")
    public fun GetCategories() : Call<CategoryTree>

    @GET("orders")
    public fun GetOrders() : Call<List<Order>>

    @POST("order/edit")
    public fun UpdateOrder(@Body newOrder : Order) : Call<Order>
}