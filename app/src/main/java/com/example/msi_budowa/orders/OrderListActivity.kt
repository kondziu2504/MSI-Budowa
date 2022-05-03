package com.example.msi_budowa.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.msi_budowa.R

class OrderListActivity : AppCompatActivity() {

    private lateinit var listView : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        listView = findViewById(R.id.orderListLV)

        val orderList = loadOrders()

        val adapter = OrderAdapter(this, orderList)
        listView.adapter = adapter

    }

    private fun loadOrders() : List<Order>{
        return listOf(Order(1,"a", "b", "Pasaż Grunwaldzki, Wrocław", OrderStatus.InProcess, "d"),
            Order(2, "Tytuł zlecenia", "Jacek Jędruszek", "osiedle Korczak, Kalisz", OrderStatus.InProcess, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut suscipit dolor sed mi condimentum bibendum. Etiam sed arcu at est elementum molestie vitae eu metus. Nulla sem leo, tincidunt eget risus eu, commodo vestibulum augue. Nullam venenatis mauris metus, at porta odio tincidunt at. Ut ac arcu eu mi porta tincidunt. Etiam lobortis magna vel nulla molestie, ut fermentum eros suscipit. Nam commodo, lectus sed tempor pellentesque, turpis dolor mattis mi, vel dignissim leo lorem at arcu."),
            Order(3, "Remont kuchni", "Konrad Stręk", "Cicha 5, Pleszew", OrderStatus.NotStarted, "Położyć nowe płytki"))
    }
}