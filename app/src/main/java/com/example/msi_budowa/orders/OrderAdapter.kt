package com.example.msi_budowa.orders

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.msi_budowa.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import java.util.*

class OrderAdapter(private val context: Context,
                   private val dataSource: List<Order>) : BaseAdapter(){

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.item_order, null, false)

        val titleTextView = rowView.findViewById(R.id.orderTitleTV) as TextView
        val statusTextView = rowView.findViewById(R.id.orderStatusTV) as TextView
        val nameTextView = rowView.findViewById(R.id.orderOrderingNameTV) as TextView
        val addressTextView = rowView.findViewById(R.id.orderAddressTV) as TextView
        val mapButton = rowView.findViewById(R.id.orderMapB) as Button

        val order = getItem(p0) as Order
        titleTextView.text = order.title
        OrderLogic.fillStatusTextView(statusTextView, context, order)

        nameTextView.text = order.orderingName
        addressTextView.text = order.address

        OrderLogic.addMapButtonListener(mapButton, context, order)

        rowView.setOnClickListener { openOrder(order) }

        return rowView
    }

    private fun openOrder(order : Order){
        var intent = Intent(context, OrderActivity::class.java)
        var bundle = Bundle()

        bundle.putParcelable("Order", order)
        intent.putExtras(bundle)

        context.startActivity(intent)
    }

}