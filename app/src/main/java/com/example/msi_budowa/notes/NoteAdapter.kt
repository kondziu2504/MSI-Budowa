package com.example.msi_budowa.notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.msi_budowa.R
import com.example.msi_budowa.orders.Order
import com.example.msi_budowa.orders.OrderActivity
import com.example.msi_budowa.orders.OrderStatus

class NoteAdapter (private val context: Context,
        private val dataSource: List<Note>) : BaseAdapter(){

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

        val rowView = inflater.inflate(R.layout.item_note, null, false)

        val titleTextView = rowView.findViewById(R.id.noteTitleTV) as TextView

        val openNoteButton = rowView.findViewById(R.id.noteOpenB) as Button

        val note = getItem(p0) as Note
        titleTextView.text = note.title

        openNoteButton.setOnClickListener { openNote(note.noteId) }

        return rowView
    }

    private fun openNote(noteId : Long){
        var intent = Intent(context, NoteActivity::class.java)

        intent.putExtra("NoteId", noteId)

        context.startActivity(intent)
    }
}