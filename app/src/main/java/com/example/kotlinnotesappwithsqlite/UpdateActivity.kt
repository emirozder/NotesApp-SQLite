package com.example.kotlinnotesappwithsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinnotesappwithsqlite.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteID = intent.getIntExtra("note_id", -1)
        if (noteID == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteID)
        binding.etUpdateNoteTitle.setText(note.title)
        binding.etUpdateNoteContent.setText(note.content)

        binding.btnSaveUpdate.setOnClickListener {
            val newTitle = binding.etUpdateNoteTitle.text.toString()
            val newContent = binding.etUpdateNoteContent.text.toString()

            val updatedNote = NotesData(noteID, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "Note updated succesfully.", Toast.LENGTH_SHORT).show()
        }
    }
}