package com.example.msi_budowa.building_products;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.example.msi_budowa.R
import com.example.msi_budowa.common.Product
import com.example.msi_budowa.common.data_source.Repository
import com.example.msi_budowa.databinding.ItemProductsListBinding
import com.example.msi_budowa.orders.Order
import com.example.msi_budowa.warehouse.WarehouseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsListActivity : AppCompatActivity(){

    private var order : Order? = null

    private lateinit var warehouseButton : Button
    private lateinit var productsListLayout: LinearLayout

    private var products : List<Product>? = null
    private var orders : List<Order>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)

        warehouseButton = findViewById(R.id.warehouseButton)
        productsListLayout = findViewById(R.id.productsListLayout)

        warehouseButton.setOnClickListener{ openWarehouse() }

        lifecycle.coroutineScope.launch{
            withContext(Dispatchers.IO){
                Repository.GetWarehouseItems { items ->
                    launch { withContext(Dispatchers.Main){
                        products = items
                        showProducts()
                    } }
                }
            }
        }

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                Repository.GetOrders { orders ->
                    launch { withContext(Dispatchers.Main) {
                        this@ProductsListActivity.orders = orders
                        order = orders.find { order -> order.id == intent.getLongExtra("OrderId", -1) }
                        showProducts()
                    } }
                }
            }
        }
    }

    private fun openWarehouse(){
        var intent = Intent(this, WarehouseActivity::class.java)
        intent.putExtra("order", order)
        startActivity(intent)
    }

    private fun showProducts() {
        if(products == null || orders == null)
            return

        var displayedProducts = products!!.filter { orderProduct -> order!!.products.contains(orderProduct.id) }

        productsListLayout.removeAllViews()

        displayedProducts.forEach{
            var productItem = ItemProductsListBinding.inflate(layoutInflater, productsListLayout, true)
            productItem.product = it
            productItem.amount = order!!.products[it.id]
        }


    }
}