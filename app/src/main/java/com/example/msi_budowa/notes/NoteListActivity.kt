package com.example.msi_budowa.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.msi_budowa.R

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

        val noteList = loadNotes(orderId)

        val adapter = NoteAdapter(this, noteList)
        listView.adapter = adapter
    }

    private fun loadNotes(orderId : Long) : List<Note>{
        return listOf(
            Note(orderId, 1, "Notatka a" + orderId, "b"),
            Note(orderId, 2, "Notatka b" + orderId, "b"),
            Note(orderId, 3, "Notatka c" + orderId, "b"))
    }
}