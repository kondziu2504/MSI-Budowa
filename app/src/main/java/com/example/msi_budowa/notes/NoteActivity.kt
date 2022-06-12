package com.example.msi_budowa.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.coroutineScope
import com.example.msi_budowa.R
import com.example.msi_budowa.common.data_source.Repository
import com.example.msi_budowa.orders.Order
import com.example.msi_budowa.orders.OrderLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteActivity : AppCompatActivity() {

    var noteId : Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val titleEditText = findViewById<EditText>(R.id.noteTitlePT)
        val descriptionEditText = findViewById<EditText>(R.id.noteDescriptionPT)

        val bundle = intent.extras
        if (bundle != null) {
            noteId = bundle.getLong("NoteId")
        }
        loadNote(noteId)

    }

//
//    private fun loadNote(noteId : Long) : Note {
//        val notes = listOf(
//            Note(1, 1, "Notatka a" + 1, "b" + noteId),
//            Note(1, 2, "Notatka b" + 1, "b" + noteId),
//            Note(1, 3, "Notatka c" + 1, "b" + noteId))
//        return notes[noteId.toInt()-1]
//    }

    private fun loadNote(noteId : Long){
        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO){
                Repository.GetNote(noteId) { note ->
                    lifecycle.coroutineScope.launch{
                        withContext(Dispatchers.Main){
                            val titleEditText = findViewById<EditText>(R.id.noteTitlePT)
                            val descriptionEditText = findViewById<EditText>(R.id.noteDescriptionPT)
                            titleEditText.setText(note.title)
                            descriptionEditText.setText(note.description)
                        }
                    }
                }
            }
        }
    }

}