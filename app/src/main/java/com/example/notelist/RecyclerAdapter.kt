package com.example.notelist

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_list_item.view.*


class RecyclerAdapter(private var notes: ArrayList<Note>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    class RecyclerViewHolder constructor(
        itemView : View
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val noteContent: TextView = itemView.lbl_content
        private val noteDate: TextView = itemView.lbl_date

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(note : Note) {
            noteContent.setText(note.content)
            noteDate.setText(note.date)
        }

        override fun onClick(p0: View?) {

            Log.d("voltou", "clickou recycler")

            val intent = Intent(itemView.context, ItemActivity::class.java).apply {
                putExtra("content", noteContent.text)
                putExtra("date", noteDate.text)
                putExtra("id", adapterPosition)
            }

            startActivityForResult(itemView.context as Activity, intent, 1, null)
        }
    }
}