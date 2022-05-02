package com.example.msi_budowa.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.msi_budowa.R

class OrderListActivity : AppCompatActivity() {

    private lateinit var listView : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        listView = findViewById<ListView>(R.id.orderListLV)

        val orderList = listOf(Order("a", "b", "c", OrderStatus.InProcess),
            Order("Tytuł zlecenia", "Jacek Jędruszek", "Adres budowy", OrderStatus.InProcess),
            Order("Remont kuchni", "Konrad Stręk", "Pleszew, ul. Cicha 5/23", OrderStatus.NotStarted))

        val adapter = OrderAdapter(this, orderList)
        listView.adapter = adapter

    }
}