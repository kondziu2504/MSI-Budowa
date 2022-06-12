package com.example.msi_budowa.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.coroutineScope
import com.example.msi_budowa.R
import com.example.msi_budowa.common.data_source.Repository
import com.example.msi_budowa.orders.OrderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var orderId : Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        listView = findViewById<ListView>(R.id.noteListLV)

        val bundle = intent.extras
        if (bundle != null) {
            orderId = bundle.getLong("OrderId")
        }

        loadNotes(orderId)

    }

    private fun loadNotes(orderId : Long){
        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO){
                Repository.GetNotes(orderId) { notes ->
                    val adapter = NoteAdapter(this@NoteListActivity, notes)
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