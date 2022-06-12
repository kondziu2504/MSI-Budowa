package com.example.msi_budowa.common.data_source.server

import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.notes.Note
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

    @GET("notes/{orderId}")
    public fun GetNotes(@Path("orderId") orderId : Long) : Call<List<Note>>

    @GET("note/{noteId}")
    public fun GetNote(@Path("noteId") orderId : Long) : Call<Note>

    @POST("order/edit")
    public fun UpdateOrder(@Body newOrder : Order) : Call<Order>
}