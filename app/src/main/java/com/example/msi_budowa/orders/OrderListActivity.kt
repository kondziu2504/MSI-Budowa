package com.example.msi_budowa.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.coroutineScope
import com.example.msi_budowa.R
import com.example.msi_budowa.common.data_source.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderListActivity : AppCompatActivity() {

    private lateinit var listView : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        listView = findViewById(R.id.orderListLV)

        loadOrders()
    }


    private fun loadOrders() {
        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO){
                Repository.GetOrders { orders ->
                    val adapter = OrderAdapter(this@OrderListActivity, orders)
                    lifecycle.coroutineScope.launch{
                        withContext(Dispatchers.Main){
                            listView.adapter = adapter
                        }
                    }
                }
            }
        }
    }
}