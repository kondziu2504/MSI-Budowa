package com.example.msi_budowa.orders

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.msi_budowa.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class OrderLogic {

    companion object{
        fun fillStatusTextView(statusTextView : TextView, context: Context, order:Order){
            statusTextView.text = context.resources.getString(order.status.idValue)

            if(order.status == OrderStatus.NotStarted){
                statusTextView.setTextColor(ContextCompat.getColor(context, R.color.order_status_not_started))
            }
            else if(order.status == OrderStatus.InProcess){
                statusTextView.setTextColor(ContextCompat.getColor(context, R.color.order_status_in_process))
            }
        }

        private fun openMap(order : Order, context: Context){
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses = geocoder.getFromLocationName(order.address, 1)

                val latitude: Float = addresses[0].latitude.toFloat()
                val longitude: Float = addresses[0].longitude.toFloat()
                val uri =
                    Uri.parse("http://maps.google.com/maps?q=loc:" + latitude + "," + longitude)
                val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                context.startActivity(intent)
            } catch (e: IOException) {
                println("Exception")
            }
        }

        fun addMapButtonListener(mapButton : Button, context: Context, order:Order){
            mapButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    if (Geocoder.isPresent()) {
                        openMap(order, context)
                    }
                }
            }
        }
    }

}