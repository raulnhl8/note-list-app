package com.example.notelist


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private lateinit var linearLayoutManager: LinearLayoutManager
private lateinit var adapter: RecyclerAdapter
var notasList : ArrayList<Note> = ArrayList()

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val addButton : FloatingActionButton = lbl_add_note_button

        adapter = RecyclerAdapter(notasList)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
//            Toast.makeText(this, "TESTE", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, 1)
        }

    }

//    override fun onStart() {
//        super.onStart()
//        if (notasList.size == 0) {
//            val novaNota : Note = Note("teste teste", "teste")
//            notasList.add(novaNota)
//            adapter.notifyItemInserted(notasList.size - 1)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == 1 && data != null) {
            val content : String = data.getStringExtra("content")!!
            val viewPos : Int = data.getIntExtra("id", -1)
//            val current = LocalDateTime.now()

            if (viewPos == -1) {
                val novaNota = Note(content, "data")
                notasList.add(novaNota)
                adapter.notifyItemInserted(notasList.size - 1)
            } else {
                notasList[viewPos].content = content
                adapter.notifyDataSetChanged()
            }
        }
    }
}
