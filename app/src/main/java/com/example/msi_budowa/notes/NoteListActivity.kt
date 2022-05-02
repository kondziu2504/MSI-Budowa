package com.example.msi_budowa.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.msi_budowa.R
import com.example.msi_budowa.orders.Order
import com.example.msi_budowa.orders.OrderAdapter
import com.example.msi_budowa.orders.OrderStatus

class NoteListActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        listView = findViewById<ListView>(R.id.noteListLV)

        val noteList = listOf(
            Note("Notatka a", "b"),
            Note("Notatka b", "b"),
            Note("Notatka c", "b")
        )

        val adapter = NoteAdapter(this, noteList)
        listView.adapter = adapter
    }
}