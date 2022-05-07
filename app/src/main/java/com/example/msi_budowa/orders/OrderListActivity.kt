package com.example.msi_budowa.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.msi_budowa.R
import com.example.msi_budowa.common.data_source.Repository

class OrderListActivity : AppCompatActivity() {

    private lateinit var listView : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        listView = findViewById(R.id.orderListLV)

        loadOrders()
    }


    private fun loadOrders() {
        return Repository.GetOrders { orders ->
            val adapter = OrderAdapter(this, orders)
            listView.adapter = adapter
        }
    }
}