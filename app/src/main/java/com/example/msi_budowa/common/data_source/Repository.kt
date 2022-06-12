package com.example.msi_budowa.common.data_source

import com.example.msi_budowa.common.CategoryTree
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.common.data_source.server.ServerDataSource
import com.example.msi_budowa.notes.Note
import com.example.msi_budowa.orders.Order

object Repository {

    private val dataSource : IDataSource =
//        MockDataSource() // Podróba danych, żeby można było se latać po aplikacji bez neta
        ServerDataSource("http://10.0.2.2:5000") // Do testowania lokalnie serwera
//        ServerDataSource("https://d0837e2d-f3fa-44c7-95f3-1987a825eeca.mock.pstmn.io") // Mockowy serwer na Postmanie

    fun GetWarehouseItems(onSuccess: (List<Product>) -> Unit){
        dataSource.GetWarehouseItems(onSuccess)
    }

    fun GetCategories(onSuccess: (CategoryTree) -> Unit){
        dataSource.GetCategories(onSuccess)
    }

    fun GetOrders(onSuccess: (List<Order>) -> Unit) {
        dataSource.GetOrders(onSuccess)
    }

    fun GetNotes(orderId : Long, onSuccess: (List<Note>) -> Unit){
        dataSource.GetNotes(orderId, onSuccess)
    }

    fun GetNote(noteId : Long, onSuccess: (Note) -> Unit){
        dataSource.GetNote(noteId, onSuccess)
    }

    fun UpdateOrder(newOrder : Order, onSuccess: () -> Unit, onFailure: () -> Unit){
        dataSource.UpdateOrder(onSuccess, onFailure)
    }
}