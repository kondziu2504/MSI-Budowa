package com.example.msi_budowa.common.data_source.server

import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.common.data_source.IDataSource
import com.example.msi_budowa.notes.Note
import com.example.msi_budowa.orders.Order
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServerDataSource(val address : String) : IDataSource {

    val service : ServerRetrofitInterface;

    init {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .registerTypeAdapter(Order::class.java, OrderDeserializer())
            .registerTypeAdapter(Note::class.java, NoteDeserializer())
            .registerTypeAdapter(Product::class.java, ProductDeserializer())
            .registerTypeAdapter(CategoryTree::class.java, CategoryTreeDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(address + "/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        service = retrofit.create(ServerRetrofitInterface::class.java)
    }


    override fun GetWarehouseItems(onSuccess: (List<Product>) -> Unit) {
        val response = service.GetWarehouseItems().execute()
        if(response.isSuccessful)
            onSuccess(response.body()!!)
    }

    override fun GetCategories(onSuccess: (CategoryTree) -> Unit) {
        val response = service.GetCategories().execute()
        if(response.isSuccessful)
            onSuccess(response.body()!!)
    }

    override fun GetOrders(onSuccess: (List<Order>) -> Unit) {
        val response = service.GetOrders().execute()
        if(response.isSuccessful)
            onSuccess(response.body()!!)
    }

    override fun GetNotes(orderId : Long, onSuccess: (List<Note>) -> Unit) {
        val response = service.GetNotes(orderId).execute()
        if(response.isSuccessful)
            onSuccess(response.body()!!)
    }

    override fun GetNote(noteId: Long, onSuccess: (Note) -> Unit) {
        val response = service.GetNote(noteId).execute()
        if(response.isSuccessful)
            onSuccess(response.body()!!)
    }

    override fun UpdateOrder(onSuccess: () -> Unit, onFailure: () -> Unit) {
        val response = service.GetOrders().execute()
        if(response.isSuccessful)
            onSuccess()
        else
            onFailure
    }
}