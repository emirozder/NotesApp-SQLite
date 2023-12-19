package com.example.kotlinnotesappwithsqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes: List<NotesData>, context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val db: NotesDatabaseHelper = NotesDatabaseHelper(context)

    class NoteViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val txt_title: TextView = itemView.findViewById(R.id.txt_itemTitle)
        val txt_content: TextView = itemView.findViewById(R.id.txt_itemContent)
        val btn_update: ImageView = itemView.findViewById(R.id.btn_update)
        val btn_delete: ImageView = itemView.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.txt_title.text = notes[position].title
        holder.txt_content.text = notes[position].content

        holder.btn_update.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                putExtra("note_id", notes[position].id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.btn_delete.setOnClickListener {
            db.deleteNote(notes[position].id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note deleted succesfully.", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newNotes: List<NotesData>){
        notes = newNotes
        notifyDataSetChanged()
    }
}