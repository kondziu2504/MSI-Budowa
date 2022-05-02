package com.example.msi_budowa.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.msi_budowa.R

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

        val order = getItem(p0) as Order
        titleTextView.text = order.title
        statusTextView.text = context.getResources().getString(order.status.idValue)

        if(order.status == OrderStatus.NotStarted){
            statusTextView.setTextColor(context.getResources().getColor(R.color.order_status_not_started))
        }
        else if(order.status == OrderStatus.InProcess){
            statusTextView.setTextColor(context.getResources().getColor(R.color.order_status_in_process))
        }

        nameTextView.text = order.orderingName
        addressTextView.text = order.address

        return rowView
    }


}