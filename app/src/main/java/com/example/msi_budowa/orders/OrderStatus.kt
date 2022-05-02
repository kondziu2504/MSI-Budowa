package com.example.msi_budowa.orders

import com.example.msi_budowa.R

enum class OrderStatus(val idValue : Int) {
    InProcess(R.string.order_status_in_process),
    NotStarted(R.string.order_status_not_started);
}