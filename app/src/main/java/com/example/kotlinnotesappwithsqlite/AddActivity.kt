package com.example.kotlinnotesappwithsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinnotesappwithsqlite.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        binding.btnSave.setOnClickListener {
            val title = binding.etNoteTitle.text.toString()
            val content = binding.etNoteContent.text.toString()
            val note = NotesData(0, title, content)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Note saved successfully.", Toast.LENGTH_SHORT).show()
        }
    }
}