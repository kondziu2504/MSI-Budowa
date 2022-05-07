package com.example.msi_budowa.orders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.msi_budowa.R
import com.example.msi_budowa.building_products.ProductsListActivity
import com.example.msi_budowa.notes.NoteListActivity

class OrderActivity : AppCompatActivity() {

    private var orderId : Long = -1

    override fun onCreate(savedBundle: Bundle?) {
        super.onCreate(savedBundle)
        setContentView(R.layout.activity_order)

        val titleTextView = findViewById<TextView>(R.id.orderTitleTV)
        val statusTextView = findViewById<TextView>(R.id.orderStatusTV)
        val nameTextView = findViewById<TextView>(R.id.orderOrderingNameTV)
        val addressTextView = findViewById<TextView>(R.id.orderAddressTV)
        val descriptionTextView = findViewById<TextView>(R.id.orderDescriptionTV)
        val mapButton = findViewById<Button>(R.id.orderMapB)

        val notesButton = findViewById<Button>(R.id.orderNotesB)
        val productsButton = findViewById<Button>(R.id.orderProductsB)

        val bundle = intent.extras
        val order = bundle?.getParcelable<Order>("Order") as Order

        titleTextView.text = order.title
        OrderLogic.fillStatusTextView(statusTextView, this, order)
        nameTextView.text = order.orderingName
        addressTextView.text = order.address
        descriptionTextView.text = order.description
        orderId = order.id

        OrderLogic.addMapButtonListener(mapButton, this, order)
        notesButton.setOnClickListener { openNotesList() }
        productsButton.setOnClickListener{ openProductsList() }
    }

    private fun openNotesList(){
        var intent = Intent(this, NoteListActivity::class.java)
        intent.putExtra("OrderId", orderId)

        startActivity(intent)
    }

    private fun openProductsList(){
        var intent = Intent(this, ProductsListActivity::class.java)
        intent.putExtra("OrderId", orderId)
        startActivity(intent)
    }
}